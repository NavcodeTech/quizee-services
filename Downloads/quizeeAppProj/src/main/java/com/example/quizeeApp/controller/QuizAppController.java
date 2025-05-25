package com.example.quizeeApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizeeApp.entity.ErrorResponse;
import com.example.quizeeApp.entity.QuestionDetails;
import com.example.quizeeApp.entity.QuizDetails;
import com.example.quizeeApp.exception.MyBusinessException;
import com.example.quizeeApp.service.AuthValidatorService;
import com.example.quizeeApp.service.QuizAppService;

@RestController
public class QuizAppController {
	
	@Autowired
	QuizAppService service;
	@Autowired
	AuthValidatorService authValidatorService;
	
	@PostMapping("/addQuestion")
	public QuestionDetails addQuestion(@RequestBody QuestionDetails questionDetails)
	{
		return service.addQuestion(questionDetails);
	}
	
	@PostMapping("/addQuizData")
	public ResponseEntity<ErrorResponse> addQuizData(@RequestHeader("Authorization") String authHeader,
			@RequestBody QuizDetails quizDetails)
	{
		System.out.println("controller"+quizDetails);
		try {
			String token = authHeader.replace("Bearer ", "");
	        Map<String, Object> userInfo = authValidatorService.validateToken(token);
	        if (userInfo == null) {
	        	ErrorResponse resp = new ErrorResponse(HttpStatus.FORBIDDEN, "Not authorized to create quizzes");
	            return new ResponseEntity<>(resp, HttpStatus.FORBIDDEN);
	        } else {

	        	service.addQuizDetails(quizDetails);
				ErrorResponse resp = new ErrorResponse(HttpStatus.OK, "Added Quiz Details Successfully");
				return new ResponseEntity<>(resp, HttpStatus.OK);
	        }
		} catch (MyBusinessException e) {
			ErrorResponse resp = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ErrorResponse resp = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getQuizDetails/{requestedCategory}")
	public List<QuizDetails> getQuizDetailsByCategory(@PathVariable("requestedCategory") String category) {
		return service.getQuizDetailsList(category);
	}
	
	@GetMapping("/filterQuizDetails")
	public ResponseEntity<Object> getFilteredQuizDetailsByCategory(@RequestParam(value="category", required=false) String category,
			 @RequestParam(value="createdBy", required=false) String createdBy, @RequestParam(value="title", required=false) String title) {
		try {
			List<QuizDetails> li = service.getFilteredQuizDetailsList(category, createdBy, title);
			return new ResponseEntity<>(li, HttpStatus.OK);
		} catch (MyBusinessException e) {
			List<QuizDetails> li = new ArrayList<QuizDetails>();
			ErrorResponse er = new ErrorResponse(HttpStatus.OK, e.getMessage());
			return new ResponseEntity<>(li, HttpStatus.OK);
		} catch (Exception e) {
			ErrorResponse er = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/updateQuizDetailsTitle")
	public String updateQuizDetailsTitle(@RequestParam(value="quizDetailsId", required=true) String quizDetailsId,
			 @RequestParam(value="updatedTitle", required=true) String updatedTitle) {
		return service.updateQuizDetailsTitle(quizDetailsId, updatedTitle);
	}
	
	@PostMapping("/updateQuizDetails")
	public ResponseEntity<ErrorResponse> updateQuizDetails(@RequestBody QuizDetails qD) {
		try {
			service.updateQuizDetails(qD);
			ErrorResponse resp = new ErrorResponse(HttpStatus.OK, "Updated Quiz Details");
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (MyBusinessException e) {
			ErrorResponse er = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ErrorResponse er = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getBulkQuizData")
	public ResponseEntity<Object> getBulkQuizDetails() {
		try {
			List<QuizDetails> li = service.getBulkQuizDetails();
			return new ResponseEntity<>(li, HttpStatus.OK);
		} catch (Exception e) {
			ErrorResponse er = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
