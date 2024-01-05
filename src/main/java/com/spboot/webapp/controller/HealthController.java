package com.spboot.webapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/health")
@RequiredArgsConstructor
public class HealthController {

    private final ApplicationContext applicationContext;

    @PutMapping(value = "/break")
    public void breakLiveness() {
        log.warn("Break Liveness");
        AvailabilityChangeEvent.publish(applicationContext, LivenessState.BROKEN);
    }
}
