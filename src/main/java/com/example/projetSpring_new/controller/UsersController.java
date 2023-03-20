package com.example.projetSpring_new.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.projetSpring_new.dto.CartItemListParam;
import com.example.projetSpring_new.dto.UsersListParam;
import com.example.projetSpring_new.service.ShoppingCartService;
import com.example.projetSpring_new.service.UsersService;



@Controller
public class UsersController {
 
	
  @Autowired
  private UsersService userService;
  @Autowired
  private ShoppingCartService cartService;
  
  @GetMapping(value = "/user/list")
  public String displayList(Model model) {
    UsersListParam userListParam = userService.searchAll();
    model.addAttribute("userListParam", userListParam);
    return "user/list";
  }
  

 
 
  
  @RequestMapping(value = "/user/listUpdate", method = RequestMethod.POST)
  public String listUpdate(@Validated @ModelAttribute UsersListParam userListParam, BindingResult result, Model model) {
    if (result.hasErrors()) {
      List<String> errorList = new ArrayList<String>();
      for (ObjectError error : result.getAllErrors()) {
        if (!errorList.contains(error.getDefaultMessage())) {
          errorList.add(error.getDefaultMessage());
        }
      }
      model.addAttribute("validationError", errorList);
      return "user/list";
    }
   
    userService.updateAll(userListParam);
    return "redirect:/user/list";
  }
}
