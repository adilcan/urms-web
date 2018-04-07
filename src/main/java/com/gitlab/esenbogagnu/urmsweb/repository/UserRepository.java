package com.gitlab.esenbogagnu.urmsweb.repository;

import com.gitlab.esenbogagnu.urmsweb.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);
}
