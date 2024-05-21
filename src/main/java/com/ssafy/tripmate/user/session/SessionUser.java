package com.ssafy.tripmate.user.session;

import java.io.Serializable;

import com.ssafy.tripmate.user.dto.User;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String id;
    private String profile;

    public SessionUser(User user){
        this.name = user.getName();
        this.id = user.getId();
        this.profile = user.getProfile();
    }
}
