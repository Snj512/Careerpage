package com.career.jobs.contoller;

import com.career.jobs.model.Job;
import com.career.jobs.service.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class CareerController {

    @Autowired
    private CareerService careerService;

    @GetMapping("/Jobs")
    public List<Job> allJobs(){
        return careerService.findAll();
    }

    @
}
