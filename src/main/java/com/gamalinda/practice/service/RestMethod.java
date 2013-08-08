package com.gamalinda.practice.service;

import android.util.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class RestMethod {

    private static RestTemplate restTemplate;
    private static RestTemplate formRestTemplate;

    public static void uploadImage(String url, MultiValueMap<String, Object> parts) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<?> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(parts, requestHeaders);

        try {
            URI result = RestMethod.getRestTemplate().postForLocation(url, requestEntity);
        } catch (Exception e) {
            Log.e("uploadImage", "error upload", e);
        }
    }

    //Rest Template
    private static RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            restTemplate = createRestTemplate();
        }

        return restTemplate;
    }

    private static RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters());
        return restTemplate;
    }

    private static List<HttpMessageConverter<?>> messageConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new MappingJacksonHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());

        ByteArrayHttpMessageConverter ba = new ByteArrayHttpMessageConverter();
        //ba.setSupportedMediaTypes(Collections.singletonList(MediaType.IMAGE_JPEG));

        messageConverters.add(new ByteArrayHttpMessageConverter());

        FormHttpMessageConverter form = new FormHttpMessageConverter();
        //form.setSupportedMediaTypes(Collections.singletonList(MediaType.IMAGE_JPEG));

        messageConverters.add(new FormHttpMessageConverter());

        return messageConverters;
    }

    //Form Rest Template
    private static RestTemplate getFormRestTemplate() {
        if (formRestTemplate == null) {
            formRestTemplate = createFormRestTemplate();
        }

        return formRestTemplate;
    }

    private static RestTemplate createFormRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(formConverters());
        return restTemplate;
    }

    private static List<HttpMessageConverter<?>> formConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.addAll(messageConverters());
        return messageConverters;
    }
}
