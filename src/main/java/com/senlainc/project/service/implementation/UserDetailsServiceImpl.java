package com.senlainc.project.service.implementation;


import com.senlainc.project.dao.interf.UserDAO;
import com.senlainc.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(userEmail);
        List<SimpleGrantedAuthority> grantedAuthorities;
        if (user == null) {
            throw new UsernameNotFoundException("Such user: "+user+ " doesn't exist!");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),user.getAuthorities());
    }
}
