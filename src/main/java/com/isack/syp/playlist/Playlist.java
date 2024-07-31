package com.isack.syp.playlist;

import com.isack.syp.audit.AuditingFields;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Playlist extends AuditingFields {

    @GeneratedValue
    @Id
    private Long id;

    private String title;

    private String thumbnailUrl;

    private Long memberId;

    private Boolean deleted = Boolean.FALSE;

    protected Playlist() {}

    private Playlist(String title, String thumbnailUrl, Long memberId) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.memberId = memberId;
    }

    public static Playlist of(String title, String thumbnailUrl, Long memberId) {
        return new Playlist(title, thumbnailUrl, memberId);
    }

    public boolean isAuthor(Long memberId) {
        if (memberId == null) {
            return false;
        }
        return this.memberId.equals(memberId);
    }
}

