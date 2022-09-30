package com.studentDebate.springMvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class HelloController {
	@RequestMapping("/")
	public String showMainPage() {
		return "main-menu";
}}
