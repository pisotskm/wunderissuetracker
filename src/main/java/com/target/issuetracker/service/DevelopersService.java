package com.target.issuetracker.service;

import com.target.issuetracker.data.DevelopersRepository;
import com.target.issuetracker.model.Developer;
import com.target.issuetracker.model.issue.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DevelopersService {
    @Autowired
    private DevelopersRepository repository;

    @RequestMapping("/developers")
    public List<Developer> developers() {
        return repository.findAll();
    }

    @RequestMapping(value = "/developers/{id}", method = RequestMethod.GET)
    public Developer developerById(@PathVariable("id") Long id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = "/developers", method = RequestMethod.POST)
    public ResponseEntity<Void> createDeveloper(@RequestBody Developer developer) {
        repository.save(developer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/developers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateDeveloper(@PathVariable("id") Long id, @RequestBody Developer developer) {
        Developer existing = repository.findOne(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repository.save(developer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/developers/{id}", method = RequestMethod.DELETE)
    @Transactional
    public ResponseEntity<Void> deleteDeveloper(@PathVariable("id") Long id) {
        Developer developer = repository.getOne(id);
        for (Issue issue : developer.getIssues()) {
            issue.setDeveloper(null);
        }
        developer.setIssues(null);
        repository.save(developer);
        repository.delete(developer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
