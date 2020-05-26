package com.bhaiti.jorhat.book.bookservice;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

	@GetMapping("/books")
	public ResponseEntity<List<String>> getBookList(){ 
		return ResponseEntity.ok(BookService.getBookList());	
	}
	
}
