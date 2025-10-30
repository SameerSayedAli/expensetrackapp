package com.sam.expensetrackapp.service.serviceimp;

import com.sam.expensetrackapp.model.User;
import com.sam.expensetrackapp.model.UserPrinciple;
import com.sam.expensetrackapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user == null){
            throw  new UsernameNotFoundException("user not found");
        }

        return new UserPrinciple(user);
    }
}
