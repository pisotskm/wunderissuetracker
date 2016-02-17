package com.target.issuetracker.business.planning;

import com.target.issuetracker.model.issue.Story;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class WeekPlan {
    private Date startDate;
    private Date endDate;
    private List<Story> stories = new ArrayList<>();

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public void addStory(Story story) {
        stories.add(story);
    }
}
