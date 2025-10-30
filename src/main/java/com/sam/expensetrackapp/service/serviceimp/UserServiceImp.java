package com.sam.expensetrackapp.service.serviceimp;

import com.sam.expensetrackapp.model.User;
import com.sam.expensetrackapp.repository.UserRepository;
import com.sam.expensetrackapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;



    @Override
    public void SaveUser(User user) {

        if(user != null) {
            userRepository.save(user);
        }

    }
}
