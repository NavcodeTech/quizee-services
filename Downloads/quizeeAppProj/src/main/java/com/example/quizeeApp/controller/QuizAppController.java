package com.example.quizeeApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizeeApp.entity.QuestionDetails;
import com.example.quizeeApp.entity.QuizDetails;
import com.example.quizeeApp.service.QuizAppService;

@RestController
public class QuizAppController {
	
	@Autowired
	QuizAppService service;
	
	@PostMapping("/addQuestion")
	public QuestionDetails addQuestion(@RequestBody QuestionDetails questionDetails)
	{
		return service.addQuestion(questionDetails);
	}
	
	@PostMapping("/addQuizData")
	public QuizDetails addQuizData(@RequestBody QuizDetails quizDetails)
	{
		System.out.println("controller"+quizDetails);
		return service.addQuizDetails(quizDetails);
	}
	
	@GetMapping("/getQuizDetails/{requestedCategory}")
	public List<QuizDetails> getQuizDetailsByCategory(@PathVariable("requestedCategory") String category) {
		return service.getQuizDetailsList(category);
	}
}
