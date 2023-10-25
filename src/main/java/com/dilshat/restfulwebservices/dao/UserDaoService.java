package com.dilshat.restfulwebservices.dao;

import com.dilshat.restfulwebservices.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static Long usersCount = 4L;

    static {
        users.add(new User(1L,"Dima", LocalDate.now().minusYears(21)));
        users.add(new User(2L,"Dara", LocalDate.now().minusYears(25)));
        users.add(new User(3L,"Ars", LocalDate.now().minusYears(23)));
    }

    public List<User> findAll(){
        return users;
    }

    public User getUserById(Long id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().get();
    }

    public void deleteUserById(Long id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

    public User createUser(User user){
        user.setId(usersCount++);
        users.add(user);
        return user;
    }
}
