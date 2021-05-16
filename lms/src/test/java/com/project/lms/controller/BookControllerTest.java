package com.project.lms.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.lms.converter.BookConverter;
import com.project.lms.dto.BookCreateUpdateDto;
import com.project.lms.dto.BookDto;
import com.project.lms.entity.BookEntity;
import com.project.lms.service.BookService;
import com.project.lms.utils.BookUtil;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {
	
	@InjectMocks
	BookController bookController;
	
	@Mock
	BookService bookService;
	
	@BeforeEach
	void setup() {
		bookController = new BookController(bookService);
	}
	
	@Test
	void givenBookList_WhenGetList_checkValidateList() {
		List<BookDto> books = new ArrayList<>();
		BookEntity bookOne = BookUtil.bookOne();
		BookEntity bookTwo = BookUtil.bookTwo();
		books.add(BookConverter.toDto(bookOne));
		books.add(BookConverter.toDto(bookTwo));
		
		Mockito.when(bookService.getAllBooks()).thenReturn(books);

		ResponseEntity<List<BookDto>> allBooks = bookController.showAllBooks();
		
		assertEquals(HttpStatus.OK, allBooks.getStatusCode());
		assertNotNull(allBooks);
		assertEquals(!books.isEmpty(),allBooks.hasBody());
		verify(bookService).getAllBooks();
	}

	@Test
	void givenNewBook_whenSave_thenVerifySave() {
		BookEntity bookThree = BookUtil.bookThree();
		BookCreateUpdateDto bookDto = new BookCreateUpdateDto();
		bookDto.setTitle(bookThree.getTitle());

		Mockito.when(bookService.createBook(bookDto)).thenReturn(BookConverter.toDto(bookThree));

		ResponseEntity<?> createBook = bookController.createBook(bookDto);

		assertNotNull(createBook);
		assertEquals(HttpStatus.CREATED, createBook.getStatusCode());
	}
	
	@Test
	void givenBookId_whenDelete_thenVerifyResponse() {
		ResponseEntity<?> deleteBookById = bookController.deleteBookById(1);
		
		assertNotNull(deleteBookById);
		assertEquals(HttpStatus.NO_CONTENT,deleteBookById.getStatusCode());
	}
	
	@Test
	void givenBook_whenAskedById_thenReturnThatBook() {
		BookEntity bookFive = BookUtil.bookFive();
		bookFive.setId(Long.valueOf(1));
		
		Mockito.when(bookService.getBookById(Long.valueOf(1))).thenReturn(BookConverter.toDto(bookFive));
		
		ResponseEntity<?> bookById = bookController.showBookById(Long.valueOf(1));
		assertNotNull(bookById);
		assertEquals(HttpStatus.OK, bookById.getStatusCode());
	}
}
