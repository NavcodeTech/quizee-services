package com.example.quizeeApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.quizeeApp.entity.QuizDetails;

@Repository
public interface QuizDetailsRepository extends JpaRepository<QuizDetails,Integer>{
	@Query("SELECT q FROM QuizDetails q LEFT JOIN FETCH q.questionList WHERE q.category = :category")
    List<QuizDetails> findByCategoryWithQuestions(@Param("category") String category);
}
