package com.mailchimp;

import com.mailchimp.auth.OAuth2RequestInterceptor;
import com.mailchimp.jackson.JacksonDecoder;
import com.mailchimp.jackson.JacksonEncoder;
import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;

import java.util.Objects;

/**
 * Builder for creating the {@link MailChimpClient}
 */
public class MailChimpClientBuilder {

    //feign specific options
    private Client client = new Client.Default(null, null);
    private Logger.Level logLevel = Logger.Level.NONE;
    private Logger logger = new Logger.JavaLogger("HttpLogger");

    //mailchimp specific options
    private RequestInterceptor authRequestInterceptor;
    private String apiBase;

    public MailChimpClientBuilder withClient(Client client){
        this.client = client;
        return this;
    }

    public MailChimpClientBuilder withLogLevel(Logger.Level logLevel){
        this.logLevel = logLevel;
        return this;
    }

    public MailChimpClientBuilder withClient(Logger logger){
        this.logger = logger;
        return this;
    }

    public MailChimpClientBuilder withBasicAuthentication(String apiKey){
        this.authRequestInterceptor = new BasicAuthRequestInterceptor("anyString", apiKey);
        return this;
    }

    public MailChimpClientBuilder withOAuth2Authentication(String accessToken){
        this.authRequestInterceptor = new OAuth2RequestInterceptor(accessToken);
        return this;
    }

    public MailChimpClientBuilder withApiBase(String apiBase){
        this.apiBase = apiBase;
        return this;
    }

    public MailChimpClient build(){
        Objects.requireNonNull(apiBase, "apiBase is required");
        Objects.requireNonNull(authRequestInterceptor, "authRequestInterceptor is required");

        MailChimpClient mailChimp = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .errorDecoder(new MailChimpErrorDecoder())
                .requestInterceptor(authRequestInterceptor)
                .client(client)
                .logger(logger)
                .logLevel(logLevel)
                .target(MailChimpClient.class, "https://" + apiBase + ".api.mailchimp.com");
        return mailChimp;
    }
}
