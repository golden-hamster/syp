package com.isack.syp.likes;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Likes {

    @GeneratedValue
    @Id
    private Long id;

    private Long memberId;

    private Long articleId;

    private Likes(Long memberId, Long articleId) {
        this.memberId = memberId;
        this.articleId = articleId;
    }

    public static Likes of(Long memberId, Long articleId) {
        return new Likes(memberId, articleId);
    }
}
