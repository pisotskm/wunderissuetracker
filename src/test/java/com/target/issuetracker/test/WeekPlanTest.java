package com.target.issuetracker.test;

import com.target.issuetracker.ApplicationConfiguration;
import com.target.issuetracker.business.planning.WeekPlan;
import com.target.issuetracker.business.planning.WeekPlanManager;
import com.target.issuetracker.business.planning.impl.WeekPlanManagerImpl;
import com.target.issuetracker.data.DevelopersRepository;
import com.target.issuetracker.data.StoriesRepository;
import com.target.issuetracker.model.Developer;
import com.target.issuetracker.model.issue.Story;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class WeekPlanTest {
    @Autowired
    private StoriesRepository storiesRepository;
    @Autowired
    private DevelopersRepository developersRepository;

    @Test
    public void testWeekPlans() {
        List<Developer> developers = developersRepository.findAll();
        List<Story> stories = storiesRepository.findAll();

        WeekPlanManager manager = new WeekPlanManagerImpl(developers, stories, story -> {});
        List<WeekPlan> plan = manager.plan(13, 1, 2016);

        int amountOfStories = plan.stream().map(WeekPlan::getStories).mapToInt(List::size).sum();
        Assert.assertEquals("amountOfStories=" + amountOfStories, stories.size(), amountOfStories);

        plan.stream().forEach(weekPlan -> {
            DateTime start = new DateTime(weekPlan.getStartDate().getTime());
            DateTime finish = new DateTime(weekPlan.getEndDate().getTime());
            Assert.assertEquals("start=" + start.getDayOfWeek(), DateTimeConstants.MONDAY, start.getDayOfWeek());
            Assert.assertEquals("finish=" + finish.getDayOfWeek(), DateTimeConstants.FRIDAY, finish.getDayOfWeek());
        });

        // check that sum of story points per week less or equal {developers count} * {max story points per developer}
        plan.stream().map(WeekPlan::getStories).forEach(storiesForDeveloper -> {
            long count = storiesForDeveloper.stream().map(Story::getDeveloper).mapToLong(Developer::getId).distinct().count();
            int sum = storiesForDeveloper.stream().mapToInt(Story::getPoint).sum();
            Assert.assertTrue("sum=" + sum + ", count=" + count,  sum <= count * WeekPlanManagerImpl.MAX_STORY_POINT_PER_DEV);
        });
    };
}
