package com.demo.library.service;

import java.util.List;

import com.demo.library.model.Book;

public interface IBookService {

	public List<Book> getAllBooks();

	public Book findBook(String bookId);

	public void upsertBook(Book book);

	public void deleteBook(Book book);

	public void deleteBookMarked(Book book);

}
