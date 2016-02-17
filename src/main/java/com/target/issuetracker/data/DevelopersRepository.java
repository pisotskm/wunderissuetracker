package com.target.issuetracker.data;

import com.target.issuetracker.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DevelopersRepository extends JpaRepository<Developer, Long> {
}
