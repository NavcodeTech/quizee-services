package com.example.quizeeApp.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@Data
@Entity
@Table(name="quiz_details")

public class QuizDetails {
	@Id
	@GeneratedValue
	private int id;
	private String title;
	private String description;
	private String category;
	private String createdBy;
	@Column(columnDefinition = "TINYINT(1)")
	private boolean questionPublished;
	private int timeLimit;
	@Column(columnDefinition = "TINYINT(1)")
	private boolean randomizeQuestion;
	private int maxAttempts;
	private int fullMarks;
	@OneToMany(mappedBy = "quizDetails", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	@Type(type = "json", parameters = @org.hibernate.annotations.Parameter(name = "json_class", value = "com.example.quizeeApp.entity.QuestionDetails"))
	private List<QuestionDetails> questionList = new ArrayList<QuestionDetails>();
	@Override
	public String toString() {
		return "QuizDetails [id=" + id + ", title=" + title + ", description=" + description + ", category=" + category
				+ ", createdBy=" + createdBy + ", questionPublished=" + questionPublished + ", timeLimit=" + timeLimit
				+ ", randomizeQuestion=" + randomizeQuestion + ", maxAttempts=" + maxAttempts + ", fullMarks="
				+ fullMarks + "]";
	}
    
	
}
