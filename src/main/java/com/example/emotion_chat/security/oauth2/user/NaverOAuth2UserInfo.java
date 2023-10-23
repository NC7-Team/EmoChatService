package com.example.emotion_chat.security.oauth2.user;

import com.example.emotion_chat.security.oauth2.user.OAuth2UserInfo;

import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo {

    public NaverOAuth2UserInfo(Map<String, Object> attributes) { super(attributes); }

    @Override
    public String getId() {return attributes.get("id").toString();}

    @Override
    public String getEmail() {return (String) attributes.get("email");}

    @Override
    public String getName() {return (String) attributes.get("name");}
}