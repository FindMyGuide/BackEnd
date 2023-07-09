package com.find_my_guide.main_member.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.find_my_guide.main_member.member.controller.MemberRestController;
import com.find_my_guide.main_member.member.domain.dto.CreateMemberRequest;
import com.find_my_guide.main_member.member.domain.dto.CreateMemberResponse;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MemberRestController.class)
class MemberRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService memberService;

    private CreateMemberRequest memberRequest;

    private Member member;

    @BeforeEach
    void setUp() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberRequest = new CreateMemberRequest(
            "abc@naver.com",
            "@qwer12345",
            "test",
            "KR",
            "MALE",
            "19980000",
            "010-1234-5678",
            "N"
        );
        member = memberRequest.toMember(passwordEncoder);
    }

    @Test
    @DisplayName("회원 등록")
    @WithMockUser
    void register() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(memberRequest);

        given(memberService.createMember(memberRequest))
            .willReturn(new CreateMemberResponse(member));

        mvc.perform(
            post("/find-my-guide/sign-up")
                .with(csrf())
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated());
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.email", equalTo("abc@naver.com")));
    }
}