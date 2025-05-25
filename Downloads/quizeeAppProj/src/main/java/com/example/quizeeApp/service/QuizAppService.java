package com.example.quizeeApp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quizeeApp.entity.QuestionDetails;
import com.example.quizeeApp.entity.QuizDetails;
import com.example.quizeeApp.exception.MyBusinessException;
import com.example.quizeeApp.repository.QuestionDetailsRepository;
import com.example.quizeeApp.repository.QuizDetailsRepository;

@Service
public class QuizAppService {
	@Autowired
	private QuestionDetailsRepository questionRepo;
	@Autowired
	private QuizDetailsRepository quizDetailsRepo;
	
	public QuestionDetails addQuestion(QuestionDetails questionDetails) {
		return questionRepo.save(questionDetails);
	}
	
	public QuizDetails addQuizDetails(QuizDetails quizDetails) {
		if(quizDetails.getQuestionList() == null || quizDetails.getQuestionList().isEmpty()) 
			throw new MyBusinessException("Quiz with empty questions can't be submitted");
		quizDetails.getQuestionList().forEach(questionDetails -> questionDetails.setQuizDetails(quizDetails));
		return quizDetailsRepo.save(quizDetails);
	}
	
	@Transactional
	public List<QuizDetails> getQuizDetailsList(String category) {
		System.out.println(category);
		List<QuizDetails> quizDetails = quizDetailsRepo.findByCategoryWithQuestions(category);
        if (quizDetails.isEmpty()) {
            throw new MyBusinessException("No quizzes found for category: " + category);
        }
        return quizDetails;
	}
	
	@Transactional
	public List<QuizDetails> getFilteredQuizDetailsList(String category, String createdBy, String title) {
		System.out.println("filtering"+category);
		List<QuizDetails> quizDetails = quizDetailsRepo.findByOptionalColumns(category, createdBy, title);
        if (quizDetails.isEmpty()) {
            throw new MyBusinessException("No quizzes found for given filtered criteria: " + category);
        }
        return quizDetails;
	} 
	
	public List<QuizDetails> getBulkQuizDetails() {
		List<QuizDetails> quizDetails = quizDetailsRepo.findAll();
        return quizDetails;
	}
	
	public String updateQuizDetailsTitle(String id, String updatedTitle) {
		
		QuizDetails q= quizDetailsRepo.findById(Integer.valueOf(id)).get();
		if (q.equals(null)) {
			return "Quiz can't be updated";
		} else {
		   q.setTitle(updatedTitle);
		   quizDetailsRepo.save(q);
		   return "Updated Successfully";
		}
	}
	
	public String updateQuizDetails(QuizDetails q) {
		QuizDetails qd= quizDetailsRepo.findById(q.getId());
		if (qd.equals(null)) {
			throw new MyBusinessException("Quiz can't be updated");
		} else {
		   quizDetailsRepo.save(q);
		   return "Updated Successfully";
		}
	}
	
}
