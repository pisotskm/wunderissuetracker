package com.target.issuetracker.model.issue.property;

public enum StoryStatus {
    NEW("New"), ESTIMATED("Estimated"), COMPLETED("Completed");

    private final String name;

    StoryStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
