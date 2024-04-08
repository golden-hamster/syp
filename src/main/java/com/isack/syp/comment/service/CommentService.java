package com.isack.syp.comment.service;

import com.isack.syp.article.domain.Article;
import com.isack.syp.article.repository.ArticleRepository;
import com.isack.syp.comment.dto.CommentDto;
import com.isack.syp.comment.repository.CommentRepository;
import com.isack.syp.member.domain.Member;
import com.isack.syp.member.dto.MemberDto;
import com.isack.syp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long saveComment(Long articleId, MemberDto memberDto, CommentDto commentDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(IllegalArgumentException::new);
        Member member = memberRepository.findById(memberDto.getId()).orElseThrow(IllegalArgumentException::new);
        return commentRepository.save(commentDto.toEntity(article, member)).getId();
    }

}
