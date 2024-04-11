package com.isack.syp.comment.service;

import com.isack.syp.article.domain.Article;
import com.isack.syp.article.repository.ArticleRepository;
import com.isack.syp.comment.domain.Comment;
import com.isack.syp.comment.dto.CommentDto;
import com.isack.syp.comment.repository.CommentRepository;
import com.isack.syp.member.domain.Member;
import com.isack.syp.member.dto.MemberDto;
import com.isack.syp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    public List<CommentDto> findByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId).stream().map(CommentDto::from).toList();
    }

    @Transactional
    public Long saveComment(Long articleId, MemberDto memberDto, CommentDto commentDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(IllegalArgumentException::new);
        Member member = memberRepository.findById(memberDto.getId()).orElseThrow(IllegalArgumentException::new);
        article.addCommentCount(); // TODO: 동시성 고려할 것
        return commentRepository.save(commentDto.toEntity(article, member)).getId();
    }

    @Transactional
    public void deleteComment(Long commentId, MemberDto memberDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(IllegalArgumentException::new);// TODO: 따로 처리할 것
        validateAuthor(memberDto, comment);
        commentRepository.delete(comment);
    }

    private void validateAuthor(MemberDto memberDto, Comment comment) {
        if (!comment.isAuthor(memberDto.getId())) {
            throw new RuntimeException();
        }
    }
}
