package com.spboot.webapp.controller;

import com.spboot.webapp.dto.PersonDto;
import com.spboot.webapp.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @PostMapping("/add")
    public long addPerson(@RequestBody PersonDto person) {
        log.info("Add person {}", person);
        return service.addPerson(person);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto findPerson(@PathVariable Long id) {
        log.info("Find person with id {}", id);
        return service.findPerson(id).orElse(null);
    }

    @PutMapping(value = "/addFriend/{id1}/{id2}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean addFriend(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) {
        log.info("Make person with id {} and with id {} friends", id1, id2);
        return service.addFriend(id1, id2);
    }
}
