package QuizApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizApplication {
    public static void main(String[] args) {
        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(new QuizQuestion("What is the capital of France?",
                new String[]{"Paris", "Berlin", "London", "Madrid"}, 0));
        questions.add(new QuizQuestion("Which planet is known as the Red Planet?",
                new String[]{"Venus", "Mars", "Jupiter", "Saturn"}, 1));

        Quiz quiz = new Quiz(questions);

        try (Scanner scanner = new Scanner(System.in)) {
            while (!quiz.isQuizEnd()) {
                QuizQuestion currentQuestion = quiz.getCurrentQuestion();
                System.out.println("Question: " + currentQuestion.getQuestion());
                String[] options = currentQuestion.getOptions();
                for (int i = 0; i < options.length; i++) {
                    System.out.println((i + 1) + ". " + options[i]);
                }

                System.out.print("Enter your answer (1-" + options.length + "): ");
                int selectedOptionIndex = scanner.nextInt() - 1;

                quiz.answerCurrentQuestion(selectedOptionIndex);
            }
        }

        System.out.println("Quiz ended. Your score: " + quiz.getScore());
    }
}

class Quiz {
    private List<QuizQuestion> questions;
    private int currentQuestionIndex;
    private int score;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
    }

    public QuizQuestion getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public void answerCurrentQuestion(int selectedOptionIndex) {
        QuizQuestion currentQuestion = getCurrentQuestion();
        if (selectedOptionIndex == currentQuestion.getCorrectOptionIndex()) {
            score++;
        }
        currentQuestionIndex++;
    }

    public int getScore() {
        return score;
    }

    public boolean isQuizEnd() {
        return currentQuestionIndex >= questions.size();
    }
}

class QuizQuestion {
    private String question;
    private String[] options;
    private int correctOptionIndex;

    public QuizQuestion(String question, String[] options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}    
