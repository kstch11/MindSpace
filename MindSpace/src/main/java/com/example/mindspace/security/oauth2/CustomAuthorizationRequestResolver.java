package com.example.mindspace.security.oauth2;

import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {
    private final OAuth2AuthorizationRequestResolver defaultResolver;

    public CustomAuthorizationRequestResolver(ClientRegistrationRepository repo) {
        this.defaultResolver = new DefaultOAuth2AuthorizationRequestResolver(repo, "/oauth2/authorize");
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
        OAuth2AuthorizationRequest authRequest = this.defaultResolver.resolve(request);
        return authRequest != null ? customizeAuthorizationRequest(authRequest, request) : null;
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String clientRegistrationId) {
        OAuth2AuthorizationRequest authRequest = this.defaultResolver.resolve(request, clientRegistrationId);
        return authRequest != null ? customizeAuthorizationRequest(authRequest, request) : null;
    }

    private OAuth2AuthorizationRequest customizeAuthorizationRequest(OAuth2AuthorizationRequest authRequest, HttpServletRequest request) {
        String role = request.getParameter("role"); // Extract the role parameter
        if (role != null) {
            // Add the role as an additional parameter
            Map<String, Object> additionalParameters = new HashMap<>(authRequest.getAdditionalParameters());
            additionalParameters.put("role", role);
            return OAuth2AuthorizationRequest.from(authRequest)
                    .additionalParameters(additionalParameters)
                    .build();
        }
        return authRequest;
    }
}
