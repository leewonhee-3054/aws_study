package com.example.aws_study;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class S3Controller {

	private final S3Service s3Service;

	@PostMapping("/upload")
	public ResponseEntity<List<String>> upload(@RequestParam("photos") List<MultipartFile> files) throws IOException {
		List<String> uploadedUrls = new ArrayList<>();

		for (MultipartFile file : files) {
			String url = s3Service.uploadFile(file);
			uploadedUrls.add(url);
		}

		return ResponseEntity.ok(uploadedUrls);
	}
}

