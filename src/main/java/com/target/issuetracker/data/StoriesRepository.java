package com.target.issuetracker.data;

import com.target.issuetracker.model.Developer;
import com.target.issuetracker.model.issue.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface StoriesRepository extends JpaRepository<Story, Long> {
    List<Story> findByDeveloper(Developer developer);
}
