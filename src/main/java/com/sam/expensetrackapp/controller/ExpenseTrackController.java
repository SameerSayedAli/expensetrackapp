package com.sam.expensetrackapp.controller;


import com.sam.expensetrackapp.model.ExpenseTrack;
import com.sam.expensetrackapp.model.UserPrinciple;
import com.sam.expensetrackapp.service.ExpenseTrackerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ExpenseTrackController {

    @Autowired
    ExpenseTrackerService expenseTrackerService;

    @GetMapping("/expensetrack")
    public String getExpensetrack(){
        return "expensetrack";
    }

    @GetMapping("/addexpense")
    public String addExpenseForm(Model model){
        model.addAttribute("expense",new ExpenseTrack());
        return "addexpense";
    }

    @PostMapping("/addexpense")
    public String sumbitExpenseForm(@ModelAttribute("expense") ExpenseTrack expenseTrack, @AuthenticationPrincipal UserPrinciple userPrinciple){
          expenseTrackerService.SaveExpenseTracker(expenseTrack,userPrinciple.getUsername());
          return "redirect:/addexpense";
    }

    @GetMapping("/expenselist")
    public String expenseList(@AuthenticationPrincipal UserPrinciple userPrinciple, Model model){
        List<ExpenseTrack> expenseTrackList = expenseTrackerService.expenaseList(userPrinciple.getUsername());
        model.addAttribute("expenseTrackList",expenseTrackList);
        return "expenselist";
    }

    @GetMapping("/update/{id}")
    public String updateExpense(@PathVariable Long id, HttpSession session){
       ExpenseTrack expenseTrack = expenseTrackerService.getExpense(id);
       session.setAttribute("expenseTrack",expenseTrack);
       session.setAttribute("id",id);
       return "redirect:/updateexpense";
    }

    @GetMapping("/updateexpense")
    public String getUpdateForm(HttpSession session, Model model){
        ExpenseTrack track = (ExpenseTrack) session.getAttribute("expenseTrack");
        System.out.println(track.getId());
        System.out.println(track.getAmount());
        System.out.println(track.getDate());
        System.out.println(track.getExpense_name());
        System.out.println(track.getDescription());
        System.out.println(track.getUser());
        model.addAttribute("expenseTrack",session.getAttribute("expenseTrack"));
        session.setAttribute("id",session.getAttribute("id"));
        return "updateexpense";
    }


    @PostMapping("/update")
    public String updateExpense(@ModelAttribute("expenseTrack") ExpenseTrack expenseTrack,HttpSession session){
        expenseTrackerService.UpdateExpense(expenseTrack,(Long) session.getAttribute("id"));
        return "redirect:/expenselist";
    }


}
