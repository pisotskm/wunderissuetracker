package com.target.issuetracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.target.issuetracker.model.issue.Issue;
import com.target.issuetracker.model.issue.Story;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Developer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="developer")
    @JsonIgnore
    private Collection<Issue> issues;

    public Developer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Collection<Issue> issues) {
        this.issues = issues;
    }
}
