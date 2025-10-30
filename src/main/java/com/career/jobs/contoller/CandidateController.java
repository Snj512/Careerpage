package com.career.jobs.contoller;

import com.career.jobs.dto.RegisterCandidateDto;
import com.career.jobs.model.Candidates;
import com.career.jobs.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

@
@RestController
@RequiredArgsConstructor
@RequestMapping("/candidate")
public class CandidateController {

    private final CandidateService candidateService;
    private final RegisterCandidateDto registerCandidateDto;

    @GetMapping("/register")
    public Candidates newcandidate(){
        return new Candidates();
    }

    @PostMapping("/register")
    public String registered(@RequestParam RegisterCandidateDto registerCandidateDto){
        if(candidateService.findByEmail(registerCandidateDto.getEmailId())){
            return "Email ID already exists.";
        }
        candidateService.save(registerCandidateDto);
        return "Registration Done Successfully";
    }

}
