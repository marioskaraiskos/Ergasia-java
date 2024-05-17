import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TriviaAPIReader {
    public List<Question> getTriviaQuestions(int amount) {
        List<Question> questions = new ArrayList<>();
        try {
            String apiUrl = "https://opentdb.com/api.php?amount=" + amount;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray questionsArray = jsonResponse.getJSONArray("results");

            for (int i = 0; i < questionsArray.length(); i++) {
                JSONObject questionObject = questionsArray.getJSONObject(i);
                String question = questionObject.getString("question");
                String correctAnswer = questionObject.getString("correct_answer");
                JSONArray incorrectAnswers = questionObject.getJSONArray("incorrect_answers");

                List<String> allAnswers = new ArrayList<>();
                // Add incorrect answers
                for (int j = 0; j < incorrectAnswers.length(); j++) {
                    allAnswers.add(incorrectAnswers.getString(j));
                }
                // Add correct answer
                allAnswers.add(correctAnswer);

                // Randomize the position of correct answer
                Collections.shuffle(allAnswers);

                questions.add(new Question(question, allAnswers, correctAnswer));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }
}
