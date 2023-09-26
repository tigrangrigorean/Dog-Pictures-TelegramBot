package com.dog.model;

import org.springframework.stereotype.Component;

@Component
public class DogResponse {
	
	    private String breed;
	    private String url;

	    public DogResponse() {
	    	
	    }
	    
		/**
		 * @param breed
		 * @param url
		 */
		public DogResponse(String breed, String url) {
			super();
			this.breed = breed;
			this.url = url;
		}
	    
	    

	    public String getBreed() {
	        return breed;
	    }

	    public void setBreed(String breed) {
	        this.breed = breed;
	    }

	    public String getUrl() {
	        return url;
	    }

	    public void setUrl(String url) {
	        this.url = url;
	}

}
