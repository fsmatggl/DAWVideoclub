package daw.videoclub.repository;

import org.springframework.data.repository.CrudRepository;

import daw.videoclub.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);
}
