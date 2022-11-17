package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.ArticleAddRequest;
import com.mustache.bbs.domain.dto.ArticleAddResponse;
import com.mustache.bbs.domain.dto.ArticleDto;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleDto getArticle(Long id) {

        Optional<Article> optArticle = articleRepository.findById(id);  //Entity
        Article article = optArticle.get();
        ArticleDto articleDto = Article.of(article);    //DTO

        return articleDto;
    }

    public ArticleAddResponse add(ArticleAddRequest dto){
        Article article = dto.toEntity(); // dto 그대로 저장할 수 없으므로 Entity로 변환
        Article savedArticle = articleRepository.save(article);
        return new ArticleAddResponse(savedArticle.getId(), savedArticle.getTitle(), savedArticle.getContents());
    }
}
