package daw.videoclub.rest.services;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import daw.videoclub.rest.model.TrailersAPITrailer;

public interface TrailersAPIService {
	
	@GET("/trailers.json?limit=5&width=560")
	List<TrailersAPITrailer> getTrailers(@Query("movie") String movie);
}
