package com.demo.library.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Serves jsp pages to the browser
 */
@Controller
public class BookController {

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "book_list";
	}
}
