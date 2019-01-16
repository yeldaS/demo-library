package com.demo.library.repository;

import java.util.List;

import com.demo.library.model.Book;

public interface IBookRepository {

	public List<Book> findAllBooks();

	public Book findBook(String bookId);

	public void upsertBook(Book book);

	public void deleteBook(Book book);

	public void deleteBookMarked(Book book);

	public List<Book> findAllDeletedFalse();

}
