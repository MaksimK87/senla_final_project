package com.senlainc.project.service.interf;

import com.senlainc.project.entity.User;

import java.util.List;


public interface UserService {

    boolean registration(User user);

    User signIn(String email, String password);

    void deleteUser(long id);

    void updateUser(User user);

    List<User> getAllUsers();

    User getUser(long id);

}
