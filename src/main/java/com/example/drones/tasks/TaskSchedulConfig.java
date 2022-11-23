package com.example.drones.tasks;

import com.example.drones.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class TaskSchedulConfig {
    @Autowired
    DispatchService dispatchService;
    // a task that will check for drones battery leveles very 10 minutes
    @Scheduled(fixedDelay = 600000)
    public void checkDronesBattery () {
        dispatchService.checkDronesBattery();
    }
}
