package com.spboot.webapp.service;

import com.spboot.webapp.dto.PersonDto;
import com.spboot.webapp.entity.Person;
import com.spboot.webapp.repository.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    public long addPerson(PersonDto personDto) {
        final var person = mapDtoToEntity(personDto);
        final var saved = repository.save(person);
        return saved.getId();
    }

    public Optional<PersonDto> findPerson(long id) {
        return repository.findById(id).map(this::mapEntityToDto);
    }

    @Transactional
    public boolean addFriend(long id1, long id2) {
        final var p1 = repository.findById(id1).orElseThrow();
        final var p2 = repository.findById(id2).orElseThrow();
        p1.getFriends().add(p2);
        p2.getFriends().add(p1);
        repository.save(p1);
        repository.save(p2);
        return true;
    }

    private Person mapDtoToEntity(PersonDto personDto) {
        final var person = new Person();
        person.setName(personDto.name());
        person.setAge(personDto.age());
        return person;
    }

    private PersonDto mapEntityToDto(Person person) {
        return new PersonDto(person.getName(), person.getAge());
    }
}
