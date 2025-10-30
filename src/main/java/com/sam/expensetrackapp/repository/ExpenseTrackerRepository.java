package com.sam.expensetrackapp.repository;

import com.sam.expensetrackapp.model.ExpenseTrack;
import com.sam.expensetrackapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseTrackerRepository extends JpaRepository<ExpenseTrack,Long> {

     List<ExpenseTrack> findByUser(User user);


}
