package daw.videoclub.persistence;

import org.springframework.data.repository.CrudRepository;

import daw.videoclub.entity.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long>{
	Movie findByName(String name);
}
