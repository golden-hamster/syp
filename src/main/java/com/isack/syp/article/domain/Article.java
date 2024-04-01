package com.isack.syp.article.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Article {

    @GeneratedValue
    @Id
    private Long id;

    private String title;

    private String content;

    private Boolean deleted = Boolean.FALSE;

    protected Article() {}

    private Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Article of(String title, String content){
        return new Article(title, content);
    }
}
