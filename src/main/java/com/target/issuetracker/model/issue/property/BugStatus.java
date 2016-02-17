package com.target.issuetracker.model.issue.property;

public enum BugStatus {
    NEW("New"), VERIFIED("Verified"), RESOLVED("Resolved");

    private final String name;

    BugStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
