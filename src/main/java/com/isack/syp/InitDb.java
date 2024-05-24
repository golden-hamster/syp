package com.isack.syp;

import com.isack.syp.article.domain.Article;
import com.isack.syp.article.domain.PlaylistItem;
import com.isack.syp.comment.domain.Comment;
import com.isack.syp.member.domain.Member;
import com.isack.syp.playlist.domain.Playlist;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final EntityManager em;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init() {

        int num = 1;
        String content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";

//        Playlist playlist = Playlist.of("PLHUkhevQftgHALH-5KjRrGcayDvollcrj", "https://i.ytimg.com/vi/Tw7dU-9AkmU/mqdefault.jpg");
//        PlaylistItem item1 = PlaylistItem.of(
//                "_nXwrx4Qyz8",
//                "고양이와 Iwamizu의 Lofi Jazz Piano • 공부할때, 집중할때, 코딩할때 • 3 hours",
//                "https://i.ytimg.com/vi/_nXwrx4Qyz8/mqdefault.jpg");
        for (int i = 1; i <= 3; i++) {
            Member member = Member.of("member" + i, "memberNickname" + i, "{noop}password123");
            em.persist(member);

            //글 생성
            for (int j = 1; j <= 100; j++) {
                Article article = Article.of(member, "Test title" + num++, content, "https://i.ytimg.com/vi/_nXwrx4Qyz8/mqdefault.jpg");
                article.setCreatedBy(member.getNickname());
                em.persist(article);
                List<PlaylistItem> playlistItems = new ArrayList<>();
                PlaylistItem item1 = PlaylistItem.of(
                        "_nXwrx4Qyz8",
                        article,
                        "고양이와 Iwamizu의 Lofi Jazz Piano • 공부할때, 집중할때, 코딩할때 • 3 hours",
                        "https://i.ytimg.com/vi/_nXwrx4Qyz8/mqdefault.jpg");
                PlaylistItem item2 = PlaylistItem.of(
                        "Uyl_KVZpYQ4",
                        article,
                        "Erikson Jayanto - Farewell (Official Audio)",
                        "https://i.ytimg.com/vi/_nXwrx4Qyz8/mqdefault.jpg");
                PlaylistItem item3 = PlaylistItem.of(
                        "DIPxnt5vnhU",
                        article,
                        "실리카겔 (Silica Gel) - T + Tik Tak Tok (feat. So!YoON!) [MV]",
                        "https://i.ytimg.com/vi/_nXwrx4Qyz8/mqdefault.jpg");
                playlistItems.add(item1);
                playlistItems.add(item2);
                playlistItems.add(item3);
                playlistItems.forEach(em::persist);
                //댓글 생성
                for (int k = 1; k <= 5; k++) {
                    Comment comment = Comment.of(article, member, "Comment is...");
                    comment.setCreatedBy(member.getNickname());
                    em.persist(comment);
                }
            }
        }
    }


}
