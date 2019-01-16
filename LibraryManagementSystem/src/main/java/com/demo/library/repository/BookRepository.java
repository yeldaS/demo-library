package com.demo.library.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.demo.library.model.Book;

@Repository
public class BookRepository implements IBookRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<Book> findAllBooks() {
		return mongoTemplate.findAll(Book.class);
	}

	@Override
	public Book findBook(String bookId) {
		return mongoTemplate.findById(bookId, Book.class);
	}

	@Override
	public void upsertBook(Book book) {
		mongoTemplate.save(book);
	}

	@Override
	public void deleteBook(Book book) {
		// delete
		mongoTemplate.remove(book);

	}

	@Override
	public void deleteBookMarked(Book book) {
		// do not delete, mark as deleted
		mongoTemplate.save(book);
	}

	@Override
	public List<Book> findAllDeletedFalse() {
		Query query = new Query();
		query.addCriteria(Criteria.where("deleted").is(false));
		query.with(new Sort(Sort.Direction.ASC, "bookName"));
		return mongoTemplate.find(query, Book.class);
	}

}
