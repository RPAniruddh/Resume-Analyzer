package com.resumeanalyzer.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.resumeanalyzer.model.Resume;
import com.resumeanalyzer.repository.ResumeRepository;
import com.resumeanalyzer.utility.SkillExtractor;

@Service
public class ResumeService {

	@Autowired
	private ResumeRepository repository;

	@Autowired
	private SkillExtractor extractor;
	

	public Resume parseResume(MultipartFile file, int id) throws IOException, TikaException{
			Tika tika = new Tika();
			//File file = new File("C:/Users/2380296/Downloads/Aniruddh R Panicker Resume.pdf");

			String content = tika.parseToString(file.getInputStream());
			String filepath = saveTextToFile(content, id);
			
			List<String> skills = extractor.extractSkills(content);
			
			Resume resume = new Resume();
			resume.setId(1);
			resume.setSkills(skills);
			resume.setParsed_file_path(filepath);

			System.out.println(filepath);

			return repository.save(resume);

	}

	public String saveTextToFile(String text, int userId) throws IOException {
		String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		String filename = "resume_" + userId + "_" + timeStamp + ".txt";

		Path path = Paths.get("parsed_resumes", filename);
		Files.createDirectories(path.getParent());
		Files.write(path, text.getBytes(StandardCharsets.UTF_8));

		return path.toString();

	}

}
