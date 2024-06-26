//package com.ssafy.tripmate.user.service;
//
//import lombok.Builder;
//import lombok.Getter;
//
//import java.util.Map;
//
//import com.ssafy.tripmate.user.dto.User;
//import com.ssafy.tripmate.user.role.Role;
//
//@Getter
//public class OAuthAttributes {
//    private Map<String, Object> attributes; // OAuth2 반환하는 유저 정보 Map
//    private String nameAttributeKey;
//    private String name;
//    private String id;
//    private String profile;
//
//    @Builder
//    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String id, String profile) {
//        this.attributes = attributes;
//        this.nameAttributeKey = nameAttributeKey;
//        this.name = name;
//        this.id = id;
//        this.profile = profile;
//    }
//
//    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
//        // 여기서 네이버와 카카오 등 구분 (ofNaver, ofKakao)
//
//        return ofGoogle(userNameAttributeName, attributes);
//    }
//
//    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
//        return OAuthAttributes.builder()
//                .name((String) attributes.get("name"))
//                .id((String) attributes.get("email"))
//                .profile((String) attributes.get("picture"))
//                .attributes(attributes)
//                .nameAttributeKey(userNameAttributeName)
//                .build();
//    }
//
//    public User toEntity(){
//        return User.builder()
//                .name(name)
//                .id(id)
//                .profile(profile)
//                .role(Role.GUEST) // 기본 권한 GUEST
//                .build();
//    }
//
//}