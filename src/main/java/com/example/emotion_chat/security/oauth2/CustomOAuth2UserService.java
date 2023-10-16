package com.example.emotion_chat.security.oauth2;

import com.example.emotion_chat.exception.OAuth2AuthenticationProcessingException;
import com.example.emotion_chat.entity.AuthProvider;
import com.example.emotion_chat.entity.User;
import com.example.emotion_chat.repository.UserRepository;
import com.example.emotion_chat.security.UserPrincipal;
import com.example.emotion_chat.security.oauth2.user.OAuth2UserInfo;
import com.example.emotion_chat.security.oauth2.user.OAuth2UserInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        System.out.println("loadUser : " + oAuth2User);
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        System.out.println("process : " + oAuth2User);

        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            System.out.println("여기탐1 : email isEmpty");
            System.out.println("email : " + oAuth2UserInfo.getEmail());
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        User user;
        if (userOptional.isPresent()) {
            System.out.println("여기탐2 : 이메일 이미 존재");
                    user = userOptional.get();
            if (!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
            return UserPrincipal.create(user, oAuth2User.getAttributes());
        } else {
            System.out.println("여기탐3 : 이메일 새로 가입");
            //카카오 info 값 받는지 확인
            System.out.println("email : " + oAuth2UserInfo.getEmail());
            System.out.println("name : " + oAuth2UserInfo.getName());
            System.out.println("id : " + oAuth2UserInfo.getId());
            if (oAuth2UserRequest.getClientRegistration().getRegistrationId().equalsIgnoreCase(AuthProvider.google.toString())) {
                //4번으로 이동
                user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
                System.out.println("여기탐6 : 구글 소셜 DB 새로 생성");
                return UserPrincipal.create(user, oAuth2User.getAttributes());
            } else if (oAuth2UserRequest.getClientRegistration().getRegistrationId().equalsIgnoreCase(AuthProvider.kakao.toString())) {
                //4번으로 이동
                user = registerKakaoNewUser(oAuth2UserRequest, oAuth2UserInfo);
                System.out.println("여기탐6 : 카카오 소셜 DB 새로 생성");
                return UserPrincipal.create(user, oAuth2User.getAttributes());
            }
        }
        return oAuth2User;
    }


    // 구글
    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();
        System.out.println("여기탐4 : 구글 유저객체 생성");
        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setProviderId(oAuth2UserInfo.getId());
        user.setName(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        return userRepository.save(user);
    }

    // 카카오
    private User registerKakaoNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        // User 엔티티 생성
        User user = new User();
        System.out.println("여기탐4: 카카오 유저객체 생성");
        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));

        // ID를 문자열로 형 변환하여 저장
        user.setProviderId(oAuth2UserInfo.getId().toString());
        user.setName(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        return userRepository.save(user);
    }


    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
        return userRepository.save(existingUser);
    }
}
