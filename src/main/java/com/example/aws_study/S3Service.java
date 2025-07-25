package com.example.aws_study;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class S3Service {

	private final S3Client s3Client;
	private final S3Config s3Config;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${cloud.aws.region.static}")
	private String region;

	public String uploadFile(MultipartFile file) throws IOException {
		String key = file.getOriginalFilename();

		PutObjectRequest putObjectRequest = PutObjectRequest.builder()
			.bucket(bucket)
			.key(key)
			.contentType(file.getContentType())
			.build();

		s3Client.putObject(putObjectRequest,
			RequestBody.fromBytes(file.getBytes()));

		return String.format("https://%s.s3.%s.amazonaws.com/%s", bucket, region, key);
	}
}

