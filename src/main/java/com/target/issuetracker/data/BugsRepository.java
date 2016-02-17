package com.target.issuetracker.data;

import com.target.issuetracker.model.issue.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugsRepository extends JpaRepository<Bug, Long> {
}
