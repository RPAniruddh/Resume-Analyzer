package com.resumeanalyzer.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resume {
	@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String parsed_file_path;
	List<String> skills;
}
