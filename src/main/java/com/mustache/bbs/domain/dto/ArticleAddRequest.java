package com.mustache.bbs.domain.dto;

import com.mustache.bbs.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleAddRequest {
    private String title;
    private String contents;

    public Article toEntity() { // -> Article에 @Builder 추가
        Article article = Article.builder()
                .title(this.title)
                .contents(this.contents) // title, content 두 개만 넣고도 article 구성 가능 -> @Builder로 인해
                .build();
        return article;
    }
}
