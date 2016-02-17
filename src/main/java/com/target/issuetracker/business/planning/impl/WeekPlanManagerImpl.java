package com.target.issuetracker.business.planning.impl;

import com.target.issuetracker.business.planning.WeekPlan;
import com.target.issuetracker.business.planning.WeekPlanManager;
import com.target.issuetracker.model.Developer;
import com.target.issuetracker.model.issue.Story;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class WeekPlanManagerImpl implements WeekPlanManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeekPlanManagerImpl.class);
    public static final int MAX_STORY_POINT_PER_DEV = 10; // can be moved to environment properties
    private final Consumer<Story> saveStory;
    private final List<Developer> developers;
    private final List<Story> stories;

    public WeekPlanManagerImpl(List<Developer> developers, List<Story> stories, Consumer<Story> saveStory) {
        this.developers = developers;
        this.stories = stories;
        this.saveStory = saveStory;
    }

    @Override
    public List<WeekPlan> plan(Integer day, Integer month, Integer year) {
        DateTime startOfWeek = startOfWeek(day, month, year);
        // sort in descending order by point
        List<Story> localStories = stories.stream().filter(story1 -> story1.getPoint() != null).collect(Collectors.toList());
        Collections.sort(localStories, (o1, o2) -> Integer.compare(o2.getPoint(), o1.getPoint()));

        List<WeekPlan> plans = new ArrayList<>();
        while (!localStories.isEmpty()) {
            // assign user stories for next week
            WeekPlan plan = new WeekPlan();
            startOfWeek = planWeekDates(startOfWeek, plan);
            for (Developer developer : developers) {
                for (Story story : planForDeveloper(localStories, MAX_STORY_POINT_PER_DEV)) {
                    story.setDeveloper(developer);
                    plan.addStory(story);
                    saveStory.accept(story);
                }
            }
            plans.add(plan);
        }
        return plans;
    }

    private DateTime startOfWeek(Integer day, Integer month, Integer year) {
        DateTime date = DateTime.parse(year + "-" + month + "-" + day);
        LOGGER.info("Planning for date " + date);
        while (date.getDayOfWeek() != DateTimeConstants.MONDAY) {
            date = date.plusDays(1);
        }
        return date;
    }

    private DateTime planWeekDates(DateTime startOfWeek, WeekPlan plan) {
        plan.setStartDate(new java.sql.Date(startOfWeek.toDate().getTime()));
        LOGGER.info("start week date " + plan.getStartDate());
        startOfWeek = startOfWeek.plusDays(4); // work days
        plan.setEndDate(new java.sql.Date(startOfWeek.toDate().getTime()));
        LOGGER.info("end week date " + plan.getEndDate());
        startOfWeek = startOfWeek.plusDays(3); // weekends
        return startOfWeek;
    }

    private List<Story> planForDeveloper(List<Story> stories, int maxStoryPoints) {
        int userPoints = 0;
        List<Story> userStories = new ArrayList<>();
        Iterator<Story> iterator = stories.iterator();
        while(iterator.hasNext()) {
            Story story = iterator.next();
            if (userPoints + story.getPoint() <= maxStoryPoints) {
                userStories.add(story);
                iterator.remove();
                userPoints += story.getPoint();
                if (userPoints == maxStoryPoints) {
                    break;
                }
            }
        }
        return userStories;
    }
}
