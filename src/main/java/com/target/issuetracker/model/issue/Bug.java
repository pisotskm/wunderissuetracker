package com.target.issuetracker.model.issue;

import com.target.issuetracker.model.issue.property.BugPriority;
import com.target.issuetracker.model.issue.property.BugStatus;

import javax.persistence.*;

@Entity
@Table(name = "issue")
@DiscriminatorValue("B")
public class Bug extends Issue {
    @Enumerated(EnumType.STRING)
    private BugPriority priority;
    @Enumerated(EnumType.STRING)
    @Column(name = "bug_status")
    private BugStatus status;

    public BugPriority getPriority() {
        return priority;
    }

    public void setPriority(BugPriority priority) {
        this.priority = priority;
    }

    public BugStatus getStatus() {
        return status;
    }

    public void setStatus(BugStatus status) {
        this.status = status;
    }
}
