package com.ar.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ar.model.User;

@Controller
public class LoginController {

	@GetMapping("/")
	public String home(Map<String, Object> model) {
		model.put("message", "Hello World");
		model.put("title", "Hello Home");
		model.put("date", new Date());
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, @ModelAttribute("userForm") User user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			System.out.println("Login Controller Called and Redirect to home");
			return "redirect:home";
		}

		System.out.println("Login Controller Called and Redirect to Login");
		model.addAttribute("loginError", false);

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "index";
	}
}
