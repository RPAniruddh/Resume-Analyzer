package com.resumeanalyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.resumeanalyzer.model.Resume;
import com.resumeanalyzer.service.ResumeService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/resume")
public class ResumeController {

	@Autowired
	ResumeService service;

	@PostMapping("/upload")
	public ResponseEntity<Resume> upoladResume(@RequestParam("file") MultipartFile file,
			@RequestParam("userId") int userId) {
		try {
			return ResponseEntity.ok(service.parseResume(file, userId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}


}
