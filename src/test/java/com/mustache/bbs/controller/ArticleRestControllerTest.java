package com.mustache.bbs.controller;

import com.mustache.bbs.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean   // @Autowired아님 --> HospitalService는 테스트를 위해 가짜 객체를 쓰겠다는 뜻
    ArticleService articleService;    // --> 가짜 객체를 쓰면 좋은점 DB와 상관없이 테스트 가능

    @Test
    @DisplayName("Json형식으로 한개의 데이터가 리턴 되는지")
    void jsonResponse() throws Exception {

        // {"id":1,"title":"aaa","content":"suin"}

        String url = String.format("/api/v1/articles/%d", 1);
        mockMvc.perform(get(url))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.contents").exists())
                .andDo(print());

    }
}