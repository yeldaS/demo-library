package com.demo.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.demo.library.model.Book;
import com.demo.library.repository.IBookRepository;

@Service
public class BookService implements IBookService {

	@Autowired
	private IBookRepository iBookRepository;

	@Override
	public Page<Book> getAllBooks(int pageNo, int nPerPage) {
		// return iBookRepository.findAllDeletedFalse();
		return iBookRepository.findAllDeletedFalsePerPage(pageNo, nPerPage);
	}

	@Override
	public List<Book> getAllBooks() {
		return iBookRepository.findAllDeletedFalse();
	}

	@Override
	public Book findBook(String bookId) {
		return iBookRepository.findBook(bookId);
	}

	@Override
	public void upsertBook(Book book) {
		iBookRepository.upsertBook(book);
	}

	@Override
	public void deleteBook(Book book) {
		iBookRepository.deleteBook(book);
	}

	@Override
	public void deleteBookMarked(Book book) {
		iBookRepository.deleteBookMarked(book);
	}

}
