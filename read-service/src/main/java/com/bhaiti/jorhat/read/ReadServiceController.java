package com.bhaiti.jorhat.read;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadServiceController {

	@Autowired
	BookClient bookClient;

	@Autowired
	LibraryClient libraryClient;

	@GetMapping(path = "/read/{bookName}", produces = "application/json")
	public ResponseEntity<String> getReadingLocation(@PathVariable(value = "bookName") String bookName) {
		String response = "Your Book - " + bookName + " is not currently available in Libaries";
		try {
			ExecutorService executor = Executors.newFixedThreadPool(2);
			// we are executing below two REST call parallel
			Future<List<String>> bl = executor.submit(() -> bookClient.getBookList());
			Future<Map<String, String>> ll = executor.submit(() -> libraryClient.getLibraryList());
			while (!bl.isDone() && !ll.isDone()) {
				Thread.sleep(100);
			}
			List<String> bookList = bl.get();
			Map<String, String> libList = ll.get();
			executor.shutdown();

			if (bookList.contains(bookName)) {
				response = "You can read this book - " + bookName + " at this Library - " + libList.get(bookName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(response);
	}
}
