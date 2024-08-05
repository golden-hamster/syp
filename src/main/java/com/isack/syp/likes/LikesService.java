package com.isack.syp.likes;

import com.isack.syp.article.domain.Article;
import com.isack.syp.article.repository.ArticleRepository;
import com.isack.syp.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    public Integer likeArticle(Long articleId, MemberDto memberDto) {
        Long memberId = memberDto.getId();
        if (likesRepository.existsByMemberIdAndArticleId(memberId, articleId)) {
            throw new RuntimeException("이미 좋아요를 누른 게시글입니다.");
        }
        Article article = articleRepository.findById(articleId).orElseThrow(RuntimeException::new);
        likesRepository.save(Likes.of(memberId, articleId));
        article.increaseLikes();
        return article.getLikesCount();
    }

    @Transactional
    public Integer unlikeArticle(Long articleId, MemberDto memberDto) {
        Long memberId = memberDto.getId();
        Article article = articleRepository.findById(articleId).orElseThrow(RuntimeException::new);
        Likes likes = likesRepository.findByMemberIdAndArticleId(memberId, articleId).orElseThrow(RuntimeException::new);
        likesRepository.delete(likes);
        article.decreaseLikes();
        return article.getLikesCount();
    }

    public boolean isLiked(Long articleId, MemberDto memberDto) {
        Long memberId = memberDto.getId();
        return likesRepository.existsByMemberIdAndArticleId(memberId, articleId);
    }
}
