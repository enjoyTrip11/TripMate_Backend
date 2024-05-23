package com.ssafy.tripmate.user.dto;

import com.ssafy.tripmate.user.role.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user")
@Getter 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column
    private String profile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String id, String password, String profile, Role role){
        this.name = name;
        this.id = id;
        this.password = password;
        this.profile = profile;
        this.role = role;
    }

    public User update(String name, String profile){
        this.name = name;
        this.profile = profile;

        return this;
    }
}
