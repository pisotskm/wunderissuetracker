package com.target.issuetracker.business.planning;

import java.util.List;

public interface WeekPlanManager {
    List<WeekPlan> plan(Integer day, Integer month, Integer year);
}
