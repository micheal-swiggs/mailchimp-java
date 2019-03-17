package com.mailchimp.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import static feign.Util.checkNotNull;

public class OAuth2RequestInterceptor implements RequestInterceptor
{
    private final String accessToken;

    /**
     * Creates an interceptor that authenticates all requests with the specified accessToken
     *
     * @param accessToken the OAuth accessToken to use for authentication
     */
    public OAuth2RequestInterceptor(String accessToken) {
        checkNotNull(accessToken, "accessToken");
        this.accessToken = "OAuth " + accessToken;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", accessToken);
    }
}