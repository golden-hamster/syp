package com.isack.syp.article.repository;

import com.isack.syp.article.domain.Article;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.isack.syp.article.domain.QArticle.*;

@Repository
public class ArticleQueryRepository {

    private final JPAQueryFactory query;

    public ArticleQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public Page<Article> findAll(String articleSearch, Pageable pageable) {
        List<Article> articles = query.select(article)
                .from(article)
                .where(likeTitle(articleSearch))
                .orderBy(article.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = query.select(article.count())
                .from(article)
                .where(likeTitle(articleSearch))
                .fetchOne();

        return new PageImpl<>(articles, pageable, total);

    }

    private BooleanExpression likeTitle(String title) {
        return StringUtils.hasText(title) ? article.title.like("%" + title + "%") : null;
    }
}
