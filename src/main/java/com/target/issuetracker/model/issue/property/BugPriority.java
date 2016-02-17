package com.target.issuetracker.model.issue.property;

public enum BugPriority {
    CRITICAL("Critical"), MAJOR("Major"), MINOR("Minor");

    private final String name;

    BugPriority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
