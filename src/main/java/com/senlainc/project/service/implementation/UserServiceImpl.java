package com.senlainc.project.service.implementation;

import com.senlainc.project.dao.interf.UserDAO;
import com.senlainc.project.entity.User;
import com.senlainc.project.service.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public boolean registration(User user) {
        return userDAO.registration(user);
    }

    @Override
    @Transactional
    public User signIn(String email, String password) {
        return userDAO.signIn(email, password);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userDAO.deleteUser(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public User getUser(long id) {
        return userDAO.getUser(id);
    }
}
