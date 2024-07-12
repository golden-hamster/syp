package com.isack.syp.articleItem;

import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@SQLDelete(sql = "UPDATE article_item SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Entity
public class ArticleItem {

    @Id
    @GeneratedValue
    private Long id;

    private Long articleId;

    private Long itemId;

    private Boolean deleted = Boolean.FALSE;

    protected ArticleItem() {}

    private ArticleItem(Long articleId, Long itemId) {
        this.articleId = articleId;
        this.itemId = itemId;
    }

    public static ArticleItem of(Long articleId, Long itemId) {
        return new ArticleItem(articleId, itemId);
    }
}
