package com.sam.expensetrackapp.service.serviceimp;

import com.sam.expensetrackapp.model.ExpenseTrack;
import com.sam.expensetrackapp.model.User;
import com.sam.expensetrackapp.repository.ExpenseTrackerRepository;
import com.sam.expensetrackapp.repository.UserRepository;
import com.sam.expensetrackapp.service.ExpenseTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseTrackerServiceImp implements ExpenseTrackerService {

    @Autowired
    ExpenseTrackerRepository expenseTrackerRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void SaveExpenseTracker(ExpenseTrack expenseTrack,String username) {

       User user = userRepository.findByUsername(username);

        if(expenseTrack != null && user != null){
            expenseTrack.setUser(user);
            expenseTrackerRepository.save(expenseTrack);
        }
    }

    @Override
    public List<ExpenseTrack> expenaseList(String username) {

      User user =  userRepository.findByUsername(username);

      if(user != null) {
         return expenseTrackerRepository.findByUser(user);

      }
         return null;
    }

    @Override
    public ExpenseTrack getExpense(Long id) {
       return expenseTrackerRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Id not found"));
    }

    @Override
    public void UpdateExpense(ExpenseTrack expenseTrack,Long id ) {



     ExpenseTrack expenseTrack1 = expenseTrackerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Id is not found"));

     if(expenseTrack1 != null){
         expenseTrack1.setExpense_name(expenseTrack.getExpense_name());
         expenseTrack1.setAmount(expenseTrack.getAmount());
         expenseTrack1.setDate(expenseTrack.getDate());
         expenseTrack1.setDescription(expenseTrack.getDescription());
         expenseTrackerRepository.save(expenseTrack1);
     }

    }
}
