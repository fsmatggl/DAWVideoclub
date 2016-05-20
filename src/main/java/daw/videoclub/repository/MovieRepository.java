package daw.videoclub.repository;

import org.springframework.data.repository.CrudRepository;

import daw.videoclub.model.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long>{
	Movie findByName(String name);
}
