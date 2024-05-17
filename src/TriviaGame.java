import java.util.List;
import java.util.Scanner;

public class TriviaGame {
    private List<Question> questions;
    private int score;

    public TriviaGame(List<Question> questions) {
        this.questions = questions;
        this.score = 0;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Trivia Game!");
        System.out.println("Please enter your username:");
        String username = scanner.nextLine();

        System.out.println("Please enter your password:");
        String password = scanner.nextLine();
        for (Question question : questions) {
            System.out.println(question.getQuestion());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }
            System.out.print("Your answer: ");
            int userChoice = scanner.nextInt();
            String userAnswer = options.get(userChoice - 1);
            if (userAnswer.equalsIgnoreCase(question.getCorrectAnswer())) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + question.getCorrectAnswer());
            }
            System.out.println();
        }
        System.out.println("Game over! Your final score is: " + score);
    }
}
