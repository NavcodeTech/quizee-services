package com.example.quizeeApp.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.Data;

@TypeDef(name = "json", typeClass = JsonType.class)
@Data
@Entity
@Table(name="question_details")
public class QuestionDetails {
	@Id
	@GeneratedValue
    private int questionId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quizDetails_id", nullable=false)
	@JsonBackReference
	private QuizDetails quizDetails;
	private String questionText;
	private String questionType;
	private int points;
	@Type(type ="json")
	@Column(columnDefinition = "JSON")
	private String[] options;
	@Type(type ="json")
	@Column(columnDefinition = "JSON")
	private String[] correctAnswer;
	private int marks;
	private int negativeMarks;

	@Override
	public String toString() {
		return "QuestionDetails [questionId=" + questionId + ", quizDetails=" + quizDetails + ", questionText="
				+ questionText + ", questionType=" + questionType + ", points=" + points + ", options="
				+ Arrays.toString(options) + ", correctAnswer=" + Arrays.toString(correctAnswer) + ", marks=" + marks
				+ ", negativeMarks=" + negativeMarks + "]";
	}
	
	
}
