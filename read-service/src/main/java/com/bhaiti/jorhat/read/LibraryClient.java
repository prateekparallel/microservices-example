package com.bhaiti.jorhat.read;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("library-service")
public interface LibraryClient {
	@GetMapping("/librarys")
    Map<String, String> getLibraryList();
}
