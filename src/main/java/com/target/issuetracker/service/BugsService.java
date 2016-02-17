package com.target.issuetracker.service;

import com.target.issuetracker.data.BugsRepository;
import com.target.issuetracker.model.issue.Bug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BugsService {
    @Autowired
    private BugsRepository repository;

    @RequestMapping("/bugs")
    public List<Bug> bugs() {
        return repository.findAll();
    }

    @RequestMapping(value = "/bugs/{id}", method = RequestMethod.GET)
    public Bug bugById(@PathVariable("id") Long id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = "/bugs", method = RequestMethod.POST)
    public ResponseEntity<Void> createBug(@RequestBody Bug story) {
        repository.save(story);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/bugs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateBug(@PathVariable("id") Long id, @RequestBody Bug story) {
        Bug existing = repository.findOne(id);
        if (existing == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        repository.save(story);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/bugs/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteBug(@PathVariable("id") Long id) {
        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
