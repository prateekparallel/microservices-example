package com.bhaiti.jorhat.read;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient("book-service")
public interface BookClient {
	
	@GetMapping("/books")
		List<String> getBookList();	
	
	//String hello(@RequestParam String msg);

}
