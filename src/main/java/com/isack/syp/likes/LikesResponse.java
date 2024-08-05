package com.isack.syp.likes;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LikesResponse {
    private Integer likesCount;

    public static LikesResponse of(Integer likesCount) {
        return new LikesResponse(likesCount);
    }
}
