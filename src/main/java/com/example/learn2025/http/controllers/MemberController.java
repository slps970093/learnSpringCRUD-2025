package com.example.learn2025.http.controllers;

import com.example.learn2025.entities.Member;
import com.example.learn2025.http.requests.member.RegisterRequest;
import com.example.learn2025.repositories.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    private MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Member member = Member.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .account(request.getAccount())
                .password(encoder.encode(request.getPassword()))
                .name(request.getFirstName() + " " + request.getLastName())
                .build();

        this.memberRepository.save(member);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
