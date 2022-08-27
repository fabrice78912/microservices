package com.fabricedev.orderserv.service;

import com.fabricedev.orderserv.dto.InventoryResponse;
import com.fabricedev.orderserv.dto.OAuthTokenRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class InventoryClientService {

    private final RestTemplate restTemplate;
    private final OAuthTokenService authTokenService;

    public List<InventoryResponse> getInventoriesBySkuCode(List<String> skuCode){
        // call to get access token
        OAuthTokenRequest tokenRequest = OAuthTokenRequest.builder()
                .clientId("newClient")
                .clientSecret("newClientSecret")
                .scope("profile write read")
                .grantType("client_credentials")
                .build();
        String accessToken = authTokenService.retrieveAccessToken(tokenRequest).getAccessToken();

        // call to get inventory by skucode
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.set(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", accessToken));

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:8088/api/inventory")
                .queryParam("skuCode", skuCode);
        List<InventoryResponse>  inventoryResponseList = restTemplate.exchange(uriComponentsBuilder.toUriString(), HttpMethod.GET, httpEntity, List.class).getBody();

        return inventoryResponseList;

    }
}
