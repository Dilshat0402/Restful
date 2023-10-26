package com.dilshat.restfulwebservices.repositories;

import com.dilshat.restfulwebservices.entitiyes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
