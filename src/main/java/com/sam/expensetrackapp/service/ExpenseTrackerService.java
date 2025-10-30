package com.sam.expensetrackapp.service;

import com.sam.expensetrackapp.model.ExpenseTrack;
import com.sam.expensetrackapp.model.User;

import java.util.List;

public interface ExpenseTrackerService {

    void SaveExpenseTracker(ExpenseTrack expenseTrack , String usermname);

    List<ExpenseTrack> expenaseList(String username);

   ExpenseTrack getExpense(Long id);

   void UpdateExpense(ExpenseTrack expenseTrack,Long id);

}
