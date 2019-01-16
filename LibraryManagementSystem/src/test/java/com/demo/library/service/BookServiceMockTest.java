package com.demo.library.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.demo.library.model.Book;
import com.demo.library.repository.BookRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceMockTest {

	@Mock
	BookRepository bookRepositoryMock;

	@InjectMocks
	BookService bookService;

	@Test
	final public void testGetAllBooks() {
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(new Book("Halide Edib Adıvar", "Ateşten Gömlek", false));
		bookList.add(new Book("Reşat Nuri Güntekin", "Çalıkuşu", false));
		bookList.add(new Book("Peyami Safa", "Dokuzuncu Hariciye Koğuşu", false));
		when(bookRepositoryMock.findAllDeletedFalse()).thenReturn(bookList);
		assertEquals(bookList, bookService.getAllBooks());
	}

	@Test
	final public void testFindBook() {
		Book book = new Book("Halide Edib Adıvar", "Ateşten Gömlek", false);
		when(bookRepositoryMock.findBook("5c36c21e059b390f18501483")).thenReturn(book);
		assertEquals(book, bookService.findBook("5c36c21e059b390f18501483"));
	}

	// @Test
	// final public void testUpsertBook() {
	// fail("Not yet implemented"); // TODO
	// }
	//
	// @Test
	// final public void testDeleteBook() {
	// fail("Not yet implemented"); // TODO
	// }
	//
	// @Test
	// final public void testDeleteBookMarked() {
	// fail("Not yet implemented"); // TODO
	// }

	// @Test
	// public void testGetAllBooks_NoBook() {
	// List<Book> bookList = new ArrayList<Book>();
	// when(bookRepositoryMock.findAllBooks()).thenReturn(bookList);
	// assertEquals(bookList, bookService.getAllBooks());
	// }

	@Test
	public void testFindBook_WrongId() {
		when(bookRepositoryMock.findBook("000000000000000000000000")).thenReturn(null);
		assertNull(bookService.findBook("000000000000000000000000"));
	}

	@Test
	public void testFindBook_EmptyId() {
		when(bookRepositoryMock.findBook("")).thenReturn(null);
		assertNull(bookService.findBook(""));
	}

}
