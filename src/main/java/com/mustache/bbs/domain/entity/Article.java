package com.mustache.bbs.domain.entity;

import com.mustache.bbs.domain.dto.ArticleDto;
import com.mustache.bbs.domain.dto.HospitalResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
    @Entity -> JPA에서 객체로 다루겠다는 선언
    @Id → @Entity가 붙어있다면 꼭 붙여주어야 합니다. Primary Key를 의미 합니다.
    @GeneratedValue → ID를 직접 생성하지 않고 자동으로 생성하도록 한 경우 붙입니다.
 */
@Entity
@Table(name = "article")
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;

    public Article(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public static ArticleDto of(Article article) {
        return new ArticleDto(article.getId(), article.getTitle(), article.getContents());
    }
}
