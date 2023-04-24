package vttp.ssf.rng.services;

import org.springframework.stereotype.Service;

import vttp.ssf.rng.models.RNG;

@Service
public class RNGService {
	
	public RNG generateNumbers(Integer inputVal) {
		
		try {
			RNG rng = RNG.create(inputVal);
			System.out.println(rng.getImages());
			return rng;
		} catch (Exception e) {
				e.printStackTrace();
				return null;
		}
	}
}
