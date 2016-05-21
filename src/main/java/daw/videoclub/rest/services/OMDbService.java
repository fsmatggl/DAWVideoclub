package daw.videoclub.rest.services;

import retrofit.http.GET;
import retrofit.http.Query;
import daw.videoclub.rest.model.OMDbMovie;

public interface OMDbService {
	
	@GET("/?y=&plot=short&r=json")
	OMDbMovie getOMDbMovie(@Query("t") String movie);
}
