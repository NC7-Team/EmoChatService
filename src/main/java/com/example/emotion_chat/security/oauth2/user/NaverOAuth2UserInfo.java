package com.example.emotion_chat.security.oauth2.user;

import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo {
    private Map<String, Object> attributes;

    public NaverOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getId() {
        // Access the ID directly from the attributes map
        return attributes.get("id").toString();
    }

    @Override
    public String getEmail() {
        // Access the email directly from the attributes map
        return attributes.get("email").toString();
    }

    @Override
    public String getName() {
        // Access the name directly from the attributes map
        return attributes.get("name").toString();
    }
}
