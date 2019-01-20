package com.demo.library.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	public Page<Book> findAllDeletedFalsePerPage(int pageNo, int nPerPage) {
		Pageable pageable = PageRequest.of(pageNo - 1, nPerPage, Sort.by(Sort.Direction.ASC, "bookName"));
		Query query = new Query().addCriteria(Criteria.where("deleted").is(false));
		return new PageImpl<Book>(mongoTemplate.find(query.with(pageable), Book.class), pageable,
				mongoTemplate.count(query, Book.class));
		// return mongoTemplate.findAll(Book.class);
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
