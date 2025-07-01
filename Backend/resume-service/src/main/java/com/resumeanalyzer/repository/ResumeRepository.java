package com.resumeanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resumeanalyzer.model.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {

}
