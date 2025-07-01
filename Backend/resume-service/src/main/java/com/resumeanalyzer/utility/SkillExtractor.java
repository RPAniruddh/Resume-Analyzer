package com.resumeanalyzer.utility;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;

import jakarta.annotation.PostConstruct;

@Component
public class SkillExtractor {
	private Set<String> skillSet = new HashSet<>();

	@PostConstruct
	public void loadSkillsFromCSV() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("/skills.csv");

		try {
			CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
			String[] nextLine;
			reader.readNext();
			while ((nextLine = reader.readNext()) != null) {
				String skill = nextLine[0].trim().toLowerCase();
				if (!skill.isEmpty()) {
					skillSet.add(skill);
				}
			}
			System.out.println("skillset" + skillSet);
		} catch (Exception e) {
			if (inputStream != null) {
				inputStream.close();// TODO: handle exception
			}
			throw e;
		}
	}
	
	public List<String> extractSkills(String resumeText) {
	    List<String> matchedSkills = new ArrayList<>();
	    String resumeLower = resumeText.toLowerCase()
	                                   .replaceAll("[^a-z0-9 ]", " ")
	                                   .replaceAll("\\s+", " ")
	                                   .trim();
	 
	    for (String skill : skillSet) {
	        String regex = "\\b" + Pattern.quote(skill) + "\\b";
	        if (Pattern.compile(regex).matcher(resumeLower).find()) {
	            matchedSkills.add(skill);
	        }
	    }
	 
	    return matchedSkills;
	}
}
