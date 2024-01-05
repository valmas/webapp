package com.spboot.webapp.controller;

import com.spboot.webapp.dto.PersonDto;
import com.spboot.webapp.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @PostMapping("/add")
    public long addPerson(@RequestBody PersonDto person) {
        return service.addPerson(person);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto addPerson(@PathVariable Long id) {
        return service.findPerson(id).orElse(null);
    }

    @PutMapping(value = "/addFriend/{id1}/{id2}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean addFriend(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) {
        return service.addFriend(id1, id2);
    }
}
