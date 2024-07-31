package com.isack.syp.playlistitem;

import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@SQLDelete(sql = "UPDATE playlist_item SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Entity
public class PlaylistItem {

    @GeneratedValue
    @Id
    private Long id;

    private Long playlistId;

    private Long itemId;

    private Boolean deleted = Boolean.FALSE;

    protected PlaylistItem() {}

    private PlaylistItem(Long playlistId, Long itemId) {
        this.playlistId = playlistId;
        this.itemId = itemId;
    }

    public static PlaylistItem of(Long playlistId, Long itemId) {
        return new PlaylistItem(playlistId, itemId);
    }
}
