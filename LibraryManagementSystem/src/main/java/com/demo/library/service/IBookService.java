package com.demo.library.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.library.model.Book;

public interface IBookService {

	public Page<Book> getAllBooks(int pageNo, int nPerPage);

	public List<Book> getAllBooks();
	
	public Book findBook(String bookId);

	public void upsertBook(Book book);

	public void deleteBook(Book book);

	public void deleteBookMarked(Book book);

}
