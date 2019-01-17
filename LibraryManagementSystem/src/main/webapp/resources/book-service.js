/**
 * 
 */

'use strict';
var libraryApp = angular
		.module('libraryApp', [ 'ui.bootstrap', 'vcRecaptcha' ]);
libraryApp.factory('BookService', [
		'$http',
		'$q',
		function($http, $q) {
			// var REST_SERVICE_URI = 'http://localhost:8080/library/books';
			var REST_SERVICE_URI = '/books';

			var factory = {
				getAllBooks : getAllBooks,
				addBook : addBook,
				updateBook : updateBook,
				deleteBook : deleteBook
			};

			return factory;

			function getAllBooks() {
				var deferred = $q.defer();
				$http.get(REST_SERVICE_URI + '/get-all-books').then(
						function(response) {
							deferred.resolve(response.data);
							return response.data;
							console.log('get all books successed');
						}, function(errResponse) {
							return $q.reject(errResponse);
							console.error('Error while getting Books');
							deferred.reject(errResponse);
						});
				return deferred.promise;
			}

			function addBook(book) {
				var deferred = $q.defer();
				$http.post(REST_SERVICE_URI + '/upsert-book', book).then(
						function(response) {
							deferred.resolve(response.data);
						}, function(errResponse) {
							console.error('Error while adding Book');
							deferred.reject(errResponse);
						});
				return deferred.promise;
			}

			function updateBook(book) {
				var deferred = $q.defer();
				$http.put(REST_SERVICE_URI + '/upsert-book', book).then(
						function(response) {
							deferred.resolve(response.data);
						}, function(errResponse) {
							console.error('Error while updating Book');
							deferred.reject(errResponse);
						});
				return deferred.promise;
			}

			function deleteBook(book) {
				console.log('deleting Book ' + book._id);
				var deferred = $q.defer();
				var dbook = {
					_id : book._id,
					bookName : book.name,
					author : book.author,
					deleted : book.deleted
				};
				$http.put(REST_SERVICE_URI + '/delete-book', book).then(
						function(response) {
							deferred.resolve(response.data);
						}, function(errResponse) {
							console.log(errResponse);
							console.error('Error while deleting Book');
							deferred.reject(errResponse);
						});
				return deferred.promise;
			}

		} ]);