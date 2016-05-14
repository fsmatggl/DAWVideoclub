package daw.videoclub.persistence;

import org.springframework.data.repository.CrudRepository;

import daw.videoclub.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String userName);
}
