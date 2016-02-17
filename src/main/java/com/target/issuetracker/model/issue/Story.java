package com.target.issuetracker.model.issue;

import com.target.issuetracker.model.issue.property.StoryStatus;

import javax.persistence.*;

@Entity
@Table(name = "issue")
@DiscriminatorValue("S")
public class Story extends Issue {
    private Integer point;
    @Enumerated(EnumType.STRING)
    @Column(name = "story_status")
    private StoryStatus status;

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public StoryStatus getStatus() {
        return status;
    }

    public void setStatus(StoryStatus status) {
        this.status = status;
    }
}
