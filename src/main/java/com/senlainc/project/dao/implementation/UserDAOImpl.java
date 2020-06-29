package com.senlainc.project.dao.implementation;

import com.senlainc.project.dao.interf.UserDAO;
import com.senlainc.project.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);


    private static SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean registration(User user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where email=:emailParam");
        query.setParameter("emailParam", user.getEmail());
        List<User> users = query.list();
        if (users.isEmpty()) {
            session.clear();
            session.saveOrUpdate(user);
            logger.debug("User: " + user + "has been registered!");
            return true;
        } else {
            logger.debug("User " + user + "already exists!");
            return false;
        }

    }

    @Override
    public User signIn(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where email=:emailParam and password=:passwordParam");
        query.setParameter("emailParam", email);
        query.setParameter("passwordParam", password);
        User user = (User) query.uniqueResult();
        logger.debug(">>>> User: "+user);
        if (user == null) {
            logger.error("Incorrect login or password! ");    // Add Exception!
        }
        return user;
    }

    @Override
    public void deleteUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from User where idUser=:userId");
        query.setParameter("userId", id);
        query.executeUpdate();

    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public List<User> getAllUsers() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User order by userName");
        List<User> users = query.list();
        return users;
    }

    @Override
    public User getUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where idUser=:userId");
        query.setParameter("userId", id);
        User user = (User) query.uniqueResult();            // Exception if null !!!
        return user;
    }
    @Transactional
    @Override
    public User findByEmail(String userEmail) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where email=:emailParam");
        query.setParameter("emailParam", userEmail);
        User user = (User) query.uniqueResult();
        if(user!=null){
            logger.debug("Find user by email: "+user);
        }
        return user;
    }



}
