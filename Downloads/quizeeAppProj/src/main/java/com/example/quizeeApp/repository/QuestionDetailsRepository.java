package com.example.quizeeApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quizeeApp.entity.QuestionDetails;

public interface QuestionDetailsRepository extends JpaRepository<QuestionDetails, Integer>{
	QuestionDetails findByQuizDetails(int id);
}
