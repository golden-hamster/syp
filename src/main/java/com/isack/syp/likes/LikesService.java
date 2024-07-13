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
    public void likeArticle(Long articleId, MemberDto memberDto) {
        Long memberId = memberDto.getId();
        Article article = articleRepository.findById(articleId).orElseThrow(RuntimeException::new);
        likesRepository.findByMemberIdAndArticleId(memberId, articleId)
                        .ifPresentOrElse(existingLike -> { // 이미 좋아요를 누른 경우, 좋아요를 취소하고 개수 감소 처리
                            likesRepository.delete(existingLike);
                            article.decreaseLikes();
                        }, () -> { // 좋아요를 누르지 않은 경우, 좋아요를 저장하고 증가 처리
                            likesRepository.save(Likes.of(memberId, articleId));
                            article.increaseLikes();
                        });
    }
}
