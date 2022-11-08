package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleDto;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {
    // Spring이 ArticleRepository 구현체(ArticleDao)를 DI (인터페이스 아님)
    // 인터페이스지만 실제 기능이 들어가있음 findAll(), findById(), save() ...
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping(value = "/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @GetMapping(value = "/{id}")
    public String selectSingle(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);

        if (!optArticle.isEmpty()){
            model.addAttribute("article", optArticle.get());
            return "/articles/show";
        }else {
            return "error";
        }

    }

    @PostMapping(value = "/posts")
    public String creatArticle(ArticleDto form) {
        log.info(form.toString());
        Article article = form.toEntity();
        articleRepository.save(article);
        return "";
    }
}
