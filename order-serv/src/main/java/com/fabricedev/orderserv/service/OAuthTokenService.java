package com.fabricedev.orderserv.service;

import com.fabricedev.orderserv.dto.OAuthTokenRequest;
import com.fabricedev.orderserv.dto.OAuthTokenResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@AllArgsConstructor
public class OAuthTokenService {

    private final RestTemplate restTemplate;
    private final  static String URI_OAUTH= "http://localhost:8089";

    public OAuthTokenResponse retrieveAccessToken(OAuthTokenRequest request){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", request.getClientId());
        params.add("client_secret", request.getClientSecret());
        params.add("grant_type", request.getGrantType());
        params.add("scope", request.getScope());

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(URI_OAUTH)
                .path("/auth/realms/authmanager/protocol/openid-connect/token");
        URI uri = uriComponentsBuilder.build().toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        return restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(params, httpHeaders), OAuthTokenResponse.class).getBody();
    }
}
