package com.ssafy.tripmate.user.dto;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="refresh_token")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String tokenKey;

    private String tokenValue;

    public RefreshToken(String tokenKey,String tokenValue){
        this.tokenKey = tokenKey;
        this.tokenValue = tokenValue;
    }
}
