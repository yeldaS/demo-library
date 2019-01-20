package com.demo.library.repository;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.library.model.Book;

public interface IBookRepository {

	public Page<Book> findAllDeletedFalsePerPage(int pageNumber, int nPerPage);

	public Book findBook(String bookId);

	public void upsertBook(Book book);

	public void deleteBook(Book book);

	public void deleteBookMarked(Book book);

	public List<Book> findAllDeletedFalse();

}
