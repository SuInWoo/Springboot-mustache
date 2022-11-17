package com.mustache.bbs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustache.bbs.domain.dto.ArticleAddRequest;
import com.mustache.bbs.domain.dto.ArticleAddResponse;
import com.mustache.bbs.domain.dto.ArticleDto;
import com.mustache.bbs.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    @Autowired
    ObjectMapper objectMapper; //ObjectMapper를 사용하여 content를 넣는다.

    @Test
    @DisplayName("Json형식으로 한개의 데이터가 리턴 되는지")
    void jsonResponse() throws Exception {

        // {"id":1,"title":"aaa","content":"suin"}
        given(articleService.getArticle(1L))
                .willReturn(new ArticleDto(1L, "aaa", "suin"));

        String url = String.format("/api/v1/articles/%d", 1);
        mockMvc.perform(get(url))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.contents").exists())
                .andDo(print());

        verify(articleService).getArticle(1L);
    }

    @Test
    @DisplayName("add 기능 테스트")
    void add() throws Exception {
        ArticleAddRequest dto = new ArticleAddRequest("제목", "내용");

        given(articleService.add(any()))
                .willReturn(new ArticleAddResponse(1l, dto.getTitle(), dto.getContents()));

        mockMvc.perform(post("/api/v1/articles")
                        .contentType(MediaType.APPLICATION_JSON) //
                        .content(objectMapper.writeValueAsBytes(new ArticleAddRequest("제목", "내용"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value("제목"))
                .andExpect(jsonPath("$.contents").exists())
                .andDo(print());


    }
}