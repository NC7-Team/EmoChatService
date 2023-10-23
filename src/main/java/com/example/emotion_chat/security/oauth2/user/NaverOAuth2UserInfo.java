package com.example.emotion_chat.security.oauth2.user;

import com.example.emotion_chat.security.oauth2.user.OAuth2UserInfo;

import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo {

    public NaverOAuth2UserInfo(Map<String, Object> attributes) { super(attributes); }

    @Override
    public String getId() { return (String) attributes.get("id"); }

    @Override
    public String getName() { return (String) attributes.get("name"); }

    // 추가: Naver OAuth2에서 이메일 주소를 가져오도록 설정
    @Override
    public String getEmail() {
        if (attributes.containsKey("response")) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            if (response.containsKey("email")) {
                return (String) response.get("email");
            }
        }
        return null; // 이메일 주소가 없는 경우 null 반환
    }
}
