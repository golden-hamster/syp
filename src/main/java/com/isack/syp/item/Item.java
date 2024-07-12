package com.isack.syp.item;

import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@SQLDelete(sql = "UPDATE item SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Entity
public class Item {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String videoId;

    private String videoTitle;

    private String thumbnailUrl;

    private Boolean deleted = Boolean.FALSE;


    protected Item() {}

    private Item(String videoId, String videoTitle, String thumbnailUrl) {
        this.videoId = videoId;
        this.videoTitle = videoTitle;
        this.thumbnailUrl = thumbnailUrl;
    }

    public static Item of(String videoId, String videoTitle, String thumbnailUrl) {
        return new Item(videoId, videoTitle, thumbnailUrl);
    }

    public void changeStatus() {
        this.deleted = !this.deleted;
    }

}
