package com.moro.rating.book.client.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RestRequest<RESPONSE> {

    protected RestTemplate restTemplate;

    protected String serviceUrl;

    protected String url;

    protected Class<RESPONSE> responseType;

    protected HttpHeaders headers = new HttpHeaders();

    protected List<Object> params = new ArrayList<>();

    protected MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

    private List<String> pathVariables = new ArrayList<>();

    public RestRequest(RestTemplate restTemplate, String serviceUrl) {
        this.restTemplate = restTemplate;
        this.serviceUrl = serviceUrl;
    }

    public ResponseEntity<RESPONSE> get() {
        return restTemplate.exchange(getUrl(), HttpMethod.GET, new HttpEntity<>(headers), responseType,
                params.toArray());
    }

    public ResponseEntity<RESPONSE> post() {
        return restTemplate.postForEntity(getUrl(), new HttpEntity<>(this.headers), responseType, params.toArray());
    }

    public <REQUEST> ResponseEntity<RESPONSE> post(REQUEST request) {
        return restTemplate.postForEntity(getUrl(), new HttpEntity<>(request, this.headers), responseType,
                params.toArray());
    }

    public RestRequest<RESPONSE> url(String url) {
        this.url = url;
        return this;
    }

    public RestRequest<RESPONSE> responseType(Class<RESPONSE> responseType) {
        this.responseType = responseType;
        return this;
    }

    public RestRequest<RESPONSE> params(Object... params) {
        this.params.addAll(Arrays.asList(params));
        return this;
    }

    public RestRequest<RESPONSE> queryParams(MultiValueMap<String, String> queryParams) {
        this.queryParams.addAll(queryParams);
        return this;
    }

    public RestRequest<RESPONSE> queryParam(String key, Object value) {
        this.queryParams.add(key, value.toString());
        return this;
    }

    public RestRequest<RESPONSE> pathVariable(String value) {
        this.pathVariables.add(value);
        return this;
    }

    public RestRequest<RESPONSE> addJsonContentTypeHeader() {
        this.headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return this;
    }

    public RestRequest<RESPONSE> addHeader(String name, String value) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(Collections.singletonMap(name, value));
        this.headers.addAll(httpHeaders);
        return this;
    }

    private String getUrl() {
        if (queryParams == null) {
            queryParams = new LinkedMultiValueMap<>();
        }
        return UriComponentsBuilder.fromHttpUrl(serviceUrl + addPathVariables()).queryParams(queryParams).build().toUriString();
    }

    private String addPathVariables() {
        String pathVariablesUrl = Optional.ofNullable(pathVariables).stream()
                .flatMap(Collection::stream)
                .collect(Collectors.joining("/"));
        return StringUtils.hasLength(pathVariablesUrl) ? "/" + pathVariablesUrl : pathVariablesUrl;
    }

}
