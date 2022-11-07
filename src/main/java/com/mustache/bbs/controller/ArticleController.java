package com.mustache.bbs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ArticleController {

    @GetMapping(value = "/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }
}
