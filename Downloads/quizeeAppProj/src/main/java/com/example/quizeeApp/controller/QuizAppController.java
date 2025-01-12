package com.example.quizeeApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/filterQuizDetails")
	public List<QuizDetails> getFilteredQuizDetailsByCategory(@RequestParam(value="category", required=false) String category,
			 @RequestParam(value="createdBy", required=false) String createdBy, @RequestParam(value="title",required=false) String title) {
		return service.getFilteredQuizDetailsList(category, createdBy, title);
	}
	
	@GetMapping("/updateQuizDetails")
	public String updateQuizDetailsById(@RequestParam(value="quizDetailsId", required=true) String quizDetailsId,
			 @RequestParam(value="updatedTitle", required=true) String updatedTitle) {
		return service.updateQuizDetails(quizDetailsId, updatedTitle);
	}
}
