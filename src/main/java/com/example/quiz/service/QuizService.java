package com.example.quiz.service;

import com.example.quiz.model.Question;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    private final List<Question> questions = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Add a few sample Java quiz questions
        questions.add(new Question(1,
                "What is the size of an int in Java?",
                List.of("8 bits", "16 bits", "32 bits", "64 bits"),
                2)); // 32 bits

        questions.add(new Question(2,
                "Which keyword is used to inherit a class in Java?",
                List.of("implements", "extends", "inherits", "uses"),
                1)); // extends

        questions.add(new Question(3,
                "Which package contains the String class?",
                List.of("java.io", "java.lang", "java.util", "java.net"),
                1)); // java.lang

        questions.add(new Question(4,
                "Which of these is NOT a Java primitive type?",
                List.of("int", "boolean", "String", "double"),
                2)); // String
    }

    public List<Question> getAllQuestions() {
        return List.copyOf(questions);
    }

    public int totalQuestions() {
        return questions.size();
    }
}
