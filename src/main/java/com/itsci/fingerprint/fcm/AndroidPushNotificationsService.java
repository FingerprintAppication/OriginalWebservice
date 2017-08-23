package com.itsci.fingerprint.fcm;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AndroidPushNotificationsService {
	private static final String FIREBASE_SERVER_KEY = "AIzaSyBAyDG2U31L_EjQmQ42dwjWU7A0Q7EqeVo";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
	
	@Async
	public CompletableFuture<String> send(String body) {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters()
        .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		/**
		https://fcm.googleapis.com/fcm/send
		Content-Type:application/json
		Authorization:key=FIREBASE_SERVER_KEY*/

		//ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		//interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
		//interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		//restTemplate.setInterceptors(interceptors);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

	    headers.add("Content-Type", "application/json");
		headers.add("Authorization", "key=" + FIREBASE_SERVER_KEY);
		
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
//		WTF
		ResponseEntity<String> response = restTemplate.exchange(FIREBASE_API_URL, HttpMethod.POST,entity, String.class);
		//String firebase response = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
		
		return CompletableFuture.completedFuture(response.toString());
		//return CompletableFuture.completedFuture(firebase);
	}
}
