package com.dog.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dog.model.DogResponse;


@Service
public class DogApiService {
	
	private final String apiKey = "live_nGRyVGmFsbOXzYRgr19E2dsllR24qhU2zXZBij6htk5KcP7EgKttTmEpzF8bgFTQ";
	private final String apiUrl = "https://api.thedogapi.com/v1/images/search";
	private final String url = apiUrl + "?api_key=" + apiKey;
	
	public String getUrl() {
	    try {   
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<DogResponse[]> responseEntity = restTemplate.getForEntity(url, DogResponse[].class);

	        DogResponse[] responses = responseEntity.getBody();

	        if (responses != null && responses.length > 0) {
	            DogResponse response = responses[0];
	            System.out.println("Image URL: " + response.getUrl());
	            return response.getUrl();
	        } else {
	            System.out.println("No response from the API");
	            return "Nothing";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Nothing";
	    }
}
}

