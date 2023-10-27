package com.dilshat.restfulwebservices.repositories;

import com.dilshat.restfulwebservices.entitiyes.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}
