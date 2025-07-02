package com.example.learn2025.http.controllers;

import com.example.learn2025.entities.Member;
import com.example.learn2025.http.requests.member.RegisterRequest;
import com.example.learn2025.repositories.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional // 測試後自動 rollback，移除這行可讓資料實際寫入
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    void testRegister() throws Exception {
        RegisterRequest reqDto = new RegisterRequest();

        reqDto.setFirstName("John");
        reqDto.setLastName("Doe");
        reqDto.setName("AAA");
        reqDto.setAccount("user");
        reqDto.setPassword("password");

        mockMvc.perform(
                post("/api/v1/member/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(reqDto))
                )
                .andExpect(status().isNoContent());

        List<Member> memberList = this.memberRepository.findAll();

        var record = memberList.getFirst();

        Assertions.assertEquals(1, memberList.size());
        Assertions.assertEquals("John", record.getFirstName());
    }
}
