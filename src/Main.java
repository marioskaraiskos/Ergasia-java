import java.util.List;

public class Main {
    public static void main(String[] args) {
        TriviaAPIReader apiReader = new TriviaAPIReader();
        List<Question> questions = apiReader.getTriviaQuestions(10);
        TriviaGame game = new TriviaGame(questions);
        game.start();
    }
}

