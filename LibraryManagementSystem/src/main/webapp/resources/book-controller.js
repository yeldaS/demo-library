/**
 * 
 */

'use strict';
var libraryApp = angular.module('libraryApp');

libraryApp.controller('BookController', [ '$scope', '$modal', '$timeout',
		'BookService', 'vcRecaptchaService',
		function($scope, $modal, $timeout, BookService, vcRecaptchaService) {
			var self = this;
			self.book = {
				_id : null,
				bookName : '',
				author : '',
				deleted : false
			};
			self.books = [];
			self.add = add;
			self.remove = remove;
			self.edit = edit;
			self.reset = reset;

			self.pageNo = '1';
			self.nPerPage = '5';
			self.maxSize = '5';

			getBooksPerPage(self.pageNo, self.nPerPage);

			function getBooksPerPage(pageNo, nPerPage) {
				$scope.spinnerShow = true;

				BookService.getAllBooksPageable(pageNo, nPerPage).then(
						function(data) {
							self.books = data.content;
							$scope.spinnerShow = false;
							$scope.pagination = {
								currentPage : data.pageable.pageNo,
								nPerPage : nPerPage,
								totalElements : data.totalElements,
								maxSize : self.maxSize
							// number of buttons for pageNo
							};
						}, function(errResponse) {
							console.error('Error while getting Books');
							$scope.spinnerShow = false;
						});
			}

			$scope.pageChanged = function(page) {
				getBooksPerPage(page, self.nPerPage);
			}

			function add() {
				reset();
				bookModal(self.book, true, "Add New ");
			}

			function remove(book) {
				console.log('id to be deleted', book._id);
				deleteModal(book);
			}

			function edit(book) {
				console.log('id to be edited', book._id);
				bookModal(book, false, "Edit ");
			}

			function reset() {
				self.book = {
					_id : null,
					bookName : '',
					author : '',
					deleted : false
				};
			}

			function bookModal(book, isCaptcha, modalTitle) {
				var bookMain = {
					_id : book._id,
					bookName : book.bookName,
					author : book.author,
					deleted : book.deleted
				};
				var modalInstance = $modal.open({
					templateUrl : 'resources/modalViews/upsert_modal.html',
					controller : 'ModalInstanceCtrl',
					scope : $scope,
					resolve : {
						book : function() {
							return bookMain;
						},
						modalTitle : function() {
							return modalTitle;
						}
					}
				});

				modalInstance.opened.then(function(showCaptcha, modalTitle) {
					// $scope.editbook = book;
					$scope.modalTitle = modalTitle;
					$scope.showCaptcha = isCaptcha;
					console.log(book.bookName);
					console.log(book.author);
				}, function() {
					console.log('Modal dismissed at: ' + new Date());
				});

				modalInstance.result.then(function(bookModal) {
					self.book = bookModal;
					getBooksPerPage(self.pageNo, self.nPerPage);
				}, function() {
					console.log('Modal dismissed at: ' + new Date());
				});
			}

			function deleteModal(book) {
				var bookMain = {
					_id : book._id,
					bookName : book.bookName,
					author : book.author,
					deleted : book.deleted
				};

				var modalInstance = $modal.open({
					templateUrl : 'resources/modalViews/delete_modal.html',
					controller : 'ModalInstanceRemoveCtrl',
					scope : $scope,
					resolve : {
						book : function() {
							return bookMain;
						}
					}
				});

				modalInstance.result.then(function() {
					getBooksPerPage(self.pageNo, self.nPerPage);
				}, function() {
					console.log('Modal dismissed at: ' + new Date());
				});
			}

		} ]);

libraryApp.controller('ModalInstanceCtrl', [
		'$scope',
		'$modalInstance',
		'BookService',
		'vcRecaptchaService',
		'book',
		'modalTitle',
		function($scope, $modalInstance, BookService, vcRecaptchaService, book,
				modalTitle) { // $scopehtmldekialan
			$scope.bookModal = {
				_id : book._id,
				bookName : book.bookName,
				author : book.author,
				deleted : book.deleted
			};
			$scope.modalTitle = modalTitle;
			$scope.errorShow = false;
			$scope.save = function() {
				$scope.cancelDisabled = true;
				console.log($scope.bookModal.bookName);
				if ($scope.bookModal.bookName == ""
						|| $scope.bookModal.bookName == null
						|| $scope.bookModal.author == ""
						|| $scope.bookModal.author == null) {
					alert("Please enter book name/author");
				} else {
					if ($scope.showCaptcha
							&& vcRecaptchaService.getResponse() == "") {
						alert("Please resolve the captcha and save!")
					} else {
						$scope.spinnerModalShow = true;
						submit($scope.bookModal);
						var post_data = { // prepare payload for request
							'g-recaptcha-response' : vcRecaptchaService
									.getResponse()
						// send
						// g-captcah-reponse
						// to
						// our
						// server
						}

					}
				}
				;
			};

			$scope.cancel = function() {
				$modalInstance.dismiss('cancel');
			};

			function addBook(book) {
				BookService.addBook(book).then(function(data) {
					$scope.spinnerModalShow = false;
					$modalInstance.close($scope.bookModal);
				}, function(errResponse) {
					$scope.spinnerModalShow = false;
					$scope.errorShow = true;
					console.error('Error while adding Book');
				});
			}

			function updateBook(book) {
				BookService.updateBook(book).then(function(data) {
					$scope.spinnerModalShow = false;
					$modalInstance.close($scope.bookModal);
				}, function(errResponse) {
					$scope.spinnerModalShow = false;
					$scope.errorShow = true;
					console.error('Error while updating Book');
				});
			}

			function submit(book) {
				if (book._id == null) {
					console.log('Saving New Book', book);
					addBook(book);
				} else {
					updateBook(book, book._id);
					console.log('Book updated with id ', book._id);
				}
				// reset();
			}

		} ]);

libraryApp.controller('ModalInstanceRemoveCtrl', [ '$scope', '$modalInstance',
		'BookService', 'book',
		function($scope, $modalInstance, BookService, book) { // $scope
			// sadece
			// htmldeki
			// alana
			// referans
			// eder
			console.log('hello delete' + book.bookName);

			$scope.errorShow = false;
			$scope.bookName = book.bookName;
			$scope.author = book.author;

			$scope.yes = function() {
				$scope.spinnerModalShow = true;
				deleteBook(book);
			};

			$scope.no = function() {
				$modalInstance.dismiss('cancel');
			};

			function deleteBook(book) {
				console.log('book id before delete:' + book._id);
				BookService.deleteBook(book).then(function(data) {
					$scope.spinnerModalShow = false;
					$modalInstance.close();
				}, function(errResponse) {
					$scope.spinnerModalShow = false;
					$scope.errorShow = true;
					console.log(errResponse);
					console.error('Error while deleting Book' + errResponse);
				});
			}
		} ]);
