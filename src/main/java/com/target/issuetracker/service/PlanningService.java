package com.target.issuetracker.service;

import com.target.issuetracker.business.planning.WeekPlan;
import com.target.issuetracker.business.planning.WeekPlanManager;
import com.target.issuetracker.business.planning.impl.WeekPlanManagerImpl;
import com.target.issuetracker.data.DevelopersRepository;
import com.target.issuetracker.data.StoriesRepository;
import com.target.issuetracker.model.Developer;
import com.target.issuetracker.model.issue.Story;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlanningService {
    @Autowired
    private DevelopersRepository developersRepository;
    @Autowired
    private StoriesRepository storiesRepository;

    /**
     * Plans user stories for current week and next weeks
     */
    @RequestMapping(value = "/plan", method = RequestMethod.GET)
    public ResponseEntity<List<WeekPlan>> plan() {
        DateTime now = DateTime.now();
        return plan(now.getDayOfMonth(), now.getMonthOfYear(), now.getYear());
    }

    /**
     * Plans stories for users for work weeks, starting for current week,
     * that includes date {dd}/{mm}/{yyyy}
     */
    @RequestMapping(value = "/plan/{dd}/{mm}/{yyyy}", method = RequestMethod.GET)
    @Transactional
    public ResponseEntity<List<WeekPlan>> plan(@PathVariable("dd") Integer day,
                                               @PathVariable("mm") Integer month,
                                               @PathVariable("yyyy") Integer year) {
        List<Developer> developers = developersRepository.findAll();
        List<Story> stories = storiesRepository.findAll();

        if (developers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        WeekPlanManager manager = new WeekPlanManagerImpl(developers, stories, story -> storiesRepository.save(story)); // constructor should be hided in more appropriate place (some business logic factory)
        List<WeekPlan> plan = manager.plan(day, month, year);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }
}
