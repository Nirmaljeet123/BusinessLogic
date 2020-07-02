package com.vacademy.vacademybackend.service;

import com.vacademy.vacademybackend.database.QuestionRepository;
import com.vacademy.vacademybackend.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question addQuestion(Question question) {
        questionRepository.save(question);
        return questionRepository.findQuestionByPostedbyAndTimestampAndTopic(question.getPostedby(), question.getTimestamp(), question.getTopic());
    }
}
