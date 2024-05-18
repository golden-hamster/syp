package com.isack.syp;

import com.isack.syp.article.domain.Article;
import com.isack.syp.comment.domain.Comment;
import com.isack.syp.member.domain.Member;
import com.isack.syp.playlist.domain.Playlist;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final EntityManager em;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init() {

        int num = 1;
        String content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

        Playlist playlist = Playlist.of("PLHUkhevQftgHALH-5KjRrGcayDvollcrj", "https://i.ytimg.com/vi/Tw7dU-9AkmU/mqdefault.jpg");
        em.persist(playlist);

        for (int i = 1; i <= 3; i++) {
            Member member = Member.of("member" + i, "memberNickname" + i, "{noop}password123");
            em.persist(member);

            //글 생성
            for (int j = 1; j <= 100; j++) {
                Article article = Article.of(member, "Test title" + num++, content, playlist);
                article.setCreatedBy(member.getNickname());
                em.persist(article);

                //댓글 생성
                for(int k = 1; k <= 5; k++){
                    Comment comment = Comment.of(article, member, "Comment is...");
                    comment.setCreatedBy(member.getNickname());
                    em.persist(comment);
                }
            }
        }
    }
}
