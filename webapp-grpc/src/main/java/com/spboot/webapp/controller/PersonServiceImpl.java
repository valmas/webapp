package com.spboot.webapp.controller;

import com.google.protobuf.Empty;
import com.spboot.webapp.dto.PersonDto;
import com.spboot.webapp.proto.*;
import com.spboot.webapp.service.PersonService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class PersonServiceImpl extends PersonServiceGrpc.PersonServiceImplBase {

    private final PersonService service;

    @Override
    public void addPerson(AddPersonRequest request, StreamObserver<AddPersonResponse> responseObserver) {
        final var personDto = new PersonDto(request.getPerson().getName(), request.getPerson().getAge());
        final var id = service.addPerson(personDto);
        final var response = AddPersonResponse.newBuilder().setId(id).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findPerson(FindPersonRequest request, StreamObserver<FindPersonResponse> responseObserver) {
        service.findPerson(request.getId())
            .map(p -> Person.newBuilder().setName(p.name()).setAge(p.age()).build())
            .map(p -> FindPersonResponse.newBuilder().setPerson(p).build())
            .ifPresent(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public void makeFriend(MakeFriendRequest request, StreamObserver<Empty> responseObserver) {
        service.addFriend(request.getId1(), request.getId2());
        responseObserver.onCompleted();
    }
}
