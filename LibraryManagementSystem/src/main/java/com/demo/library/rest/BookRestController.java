package com.demo.library.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.library.model.Book;
import com.demo.library.service.IBookService;

@RestController
@RequestMapping("/books")
public class BookRestController {

	@Autowired
	private IBookService iBookService;

	@RequestMapping(value = "/get-all-books-pageable")
	public @ResponseBody Page<Book> getBooksPerPage(@RequestParam int pageNo, @RequestParam int nPerPage) {
		return iBookService.getAllBooks(pageNo, nPerPage);
	}
	@RequestMapping(value = "/get-all-books")
	public @ResponseBody List<Book> getAllBooks() {
		return iBookService.getAllBooks();
	}

	@RequestMapping("/get-book/{bookId}")
	public @ResponseBody Book findBook(@PathVariable String bookId) {
		return iBookService.findBook(bookId);
	}

	@RequestMapping(value = "/upsert-book", headers = "content-Type=application/json")
	public @ResponseBody Book upsertBook(@RequestBody Book book) {
		iBookService.upsertBook(book);
		return book;

	}

	@RequestMapping(value = "/delete-book", method = RequestMethod.DELETE, headers = "content-Type=application/json")
	public @ResponseBody Book deleteBook(@RequestBody Book book) {
		iBookService.deleteBook(book);
		return book;
	}

	@RequestMapping(value = "/delete-book", headers = "content-Type=application/json")
	public @ResponseBody Book deleteBookMarked(@RequestBody Book book) {
		// not delete, mark as deleted
		book.setDeleted(true);
		iBookService.upsertBook(book);
		return book;
	}

}
