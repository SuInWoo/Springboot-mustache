package com.mustache.bbs.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
    @Entity -> JPA에서 객체로 다루겠다는 선언
    @Id → @Entity가 붙어있다면 꼭 붙여주어야 합니다. Primary Key를 의미 합니다.
    @GeneratedValue → ID를 직접 생성하지 않고 자동으로 생성하도록 한 경우 붙입니다.
 */
@Entity
@NoArgsConstructor
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db에 id생성을 맡기겠다는 의미
    private Long id;

    private String title;
    private String contents;

    public Article(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
