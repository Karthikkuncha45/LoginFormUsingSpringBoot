package com.thymeleaf3.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.thymeleaf3.entity.User;
import com.thymeleaf3.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("users",userservice.getAll());
		return "index";
	}
	
	@GetMapping("/addUserForm")
	public String addUserForm(Model model) {
		User u =new User();
		model.addAttribute("adduser", u);
		return "add_user";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user) {
		userservice.saveUser(user);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormUpdate(@PathVariable Long id, Model model) {
		Optional<User> user = userservice.getUserByid(id);
		if (user.isPresent()) {
			model.addAttribute("user", user.get()); // Use actual User, not Optional
			return "updateUserForm";
		} else {
			// Optional: handle user not found (redirect or show error page)
			return "redirect:/";
		}
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable Long id) {
		userservice.deleteByid(id);
		return "redirect:/";
	}
}
