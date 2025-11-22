package com.example.quiz.controller;

import com.example.quiz.model.Question;
import com.example.quiz.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("total", quizService.totalQuestions());
        return "index";
    }

    @GetMapping("/quiz")
    public String quiz(Model model) {
        List<Question> questions = quizService.getAllQuestions();
        model.addAttribute("questions", questions);
        return "quiz";
    }

    @PostMapping("/submit")
    public String submit(HttpServletRequest request, Model model) {
        List<Question> questions = quizService.getAllQuestions();
        Map<String, String[]> params = request.getParameterMap();

        int score = 0;
        for (Question q : questions) {
            String paramName = "q-" + q.getId();
            if (params.containsKey(paramName)) {
                try {
                    int selected = Integer.parseInt(request.getParameter(paramName));
                    if (selected == q.getCorrectIndex()) {
                        score++;
                    }
                } catch (NumberFormatException ignored) {}
            }
        }

        model.addAttribute("score", score);
        model.addAttribute("total", questions.size());
        return "result";
    }
}
