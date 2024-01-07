package com.example.mindspace.security.oauth2.user;


import com.example.mindspace.exception.OAuth2AuthenticationProcessingException;

import java.util.Map;


public class OAuth2UserInfoFactory {

    /**
     * Retrieves OAuth2 user information based on the registration ID and user attributes.
     * Currently, this method supports user information retrieval for Google accounts.
     *
     * @param registrationId The ID of the OAuth2 registration service (e.g., "google").
     * @param attributes     A map containing the OAuth2 user's attributes.
     * @return An instance of OAuth2UserInfo with the user's information.
     * @throws OAuth2AuthenticationProcessingException if the login with the provided registration ID is not supported.
     */
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase("google")) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
