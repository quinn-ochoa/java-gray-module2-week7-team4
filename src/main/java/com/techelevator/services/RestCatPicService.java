package com.techelevator.services;

import ch.qos.logback.classic.Logger;
import org.springframework.stereotype.Component;
import com.techelevator.model.CatPic;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;


@Component
public class RestCatPicService implements CatPicService {

	private static final String API_URL = "https://cat-data.netlify.app/api/pictures/random";
	private static final RestTemplate restTemplate = new RestTemplate();

	@Override
	public CatPic getPic() {
		CatPic catPic = null;
		try{
			catPic = restTemplate.getForObject(API_URL, CatPic.class );
		} catch (RestClientResponseException | ResourceAccessException e) {
			e.getMessage();
		}

		return catPic;
	}

}	
