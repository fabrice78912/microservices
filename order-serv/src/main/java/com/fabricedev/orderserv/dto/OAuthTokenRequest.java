package com.fabricedev.orderserv.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OAuthTokenRequest {
    private String clientId;
    private String clientSecret;
    @JsonProperty("grant_type")
    private String grantType;
    private String scope;
    @JsonProperty("redirect_uri")
    private String redirectUri;
}
