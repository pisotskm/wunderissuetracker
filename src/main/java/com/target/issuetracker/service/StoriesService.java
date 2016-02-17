package com.target.issuetracker.service;

import com.target.issuetracker.data.StoriesRepository;
import com.target.issuetracker.model.issue.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoriesService {
    @Autowired
    private StoriesRepository repository;

    @RequestMapping("/stories")
    public List<Story> stories() {
        return repository.findAll();
    }

    @RequestMapping(value = "/stories/{id}", method = RequestMethod.GET)
    public Story storyById(@PathVariable("id") Long id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = "/stories", method = RequestMethod.POST)
    public ResponseEntity<Void> createStory(@RequestBody Story story) {
        repository.save(story);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/stories/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateStory(@PathVariable("id") Long id, @RequestBody Story story) {
        Story existing = repository.findOne(id);
        if (existing == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        repository.save(story);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/stories/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteStory(@PathVariable("id") Long id) {
        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
