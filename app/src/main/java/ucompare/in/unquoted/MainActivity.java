package ucompare.in.unquoted;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int currentQuestionIndex;
    int totalCorrect;
    int totalQuestions;
    ArrayList<Question> questions;

    // TODO 3-A: Declare View member variables
    ImageView questionImageView;
    TextView questionTextView;

    TextView questionsRemainingTextView;
    Button answer0Button;
    Button answer1Button;
    Button answer2Button;
    Button answer3Button;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO 2-G: Show app icon in ActionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_unquote_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);

        setContentView(R.layout.activity_main);

        // TODO 3-B: assign View member variables
        questionsRemainingTextView = findViewById(R.id.tv_main_questions_remaining_count);
        questionImageView = findViewById(R.id.iv_main_question_image);
        questionTextView = findViewById(R.id.tv_main_question_title);
        answer0Button = findViewById(R.id.btn_main_answer_0);
        answer1Button = findViewById(R.id.btn_main_answer_1);
        answer2Button = findViewById(R.id.btn_main_answer_2);
        answer3Button = findViewById(R.id.btn_main_answer_3);
        submitButton = findViewById(R.id.btn_main_submit_answer);

        // TODO 4-E: set onClickListener for each answer Button

        // TODO 5-A: set onClickListener for the submit answer Button

        startNewGame();
    }

    // TODO 3-F: displayQuestion(Question question) {...}

    // TODO 3-C: displayQuestionsRemaining(int questionRemaining) {...}
    void displayQuestionsRemaining(int questionRemaining){

        questionsRemainingTextView.setText(questionRemaining);
    }

    // TODO 4-A: onAnswerSelected(int answerSelected) {...}

    void onAnswerSubmission() {
        Question currentQuestion = getCurrentQuestion();
        if (currentQuestion.isCorrect()) {
            totalCorrect = totalCorrect + 1;
        }
        questions.remove(currentQuestion);

        // TODO 3-D.i: Uncomment the line below after implementing displayQuestionsRemaining(int)
         displayQuestionsRemaining(questions.size());

        if (questions.size() == 0) {
            String gameOverMessage = getGameOverMessage(totalCorrect, totalQuestions);

            // TODO 5-D: Show a popup instead
            System.out.println(gameOverMessage);
        } else {
            chooseNewQuestion();

            // TODO 3-H.i: uncomment after implementing displayQuestion(Question)
            //displayQuestion(getCurrentQuestion());
        }
    }

    void startNewGame() {
        questions = new ArrayList<>();

        // TODO 2-H: Provide actual drawables for each of these questions!
        Question question0 = new Question(R.drawable.img_quote_0,
                "Pretty good advice,\n" +
                "and perhaps a scientist\n" +
                "did say it… Who\n" +
                "actually did?",
                "Albert Einstein ",
                "Isaac Newton",
                "Rita Mae Brown",
                "Rosalind Franklin", 2);
        Question question1 = new Question(R.drawable.img_quote_1,
                "Was honest Abe\n" +
                        "honestly quoted? Who\n" +
                        "authored this pithy bit\n" +
                        "of wisdom?",
                "Edward Stieglitz",
                "Maya Angelou",
                "TAbraham Lincoln",
                "Ralph Waldo Emerson",
                0);
        Question question2 = new Question(R.drawable.img_quote_2,
                "This is a test question!",
                "Test Answer 0",
                "Test Answer 1",
                "Test Answer 2",
                "Test Answer 3",
                2);
        Question question3 = new Question(R.drawable.img_quote_3,
                "Easy advice to read,\n" +
                        "difficult advice to\n" +
                        "follow — who actually said it?",
                "Martin Luther King Jr",
                "Mother Teresa",
                "Fred Rogers",
                "Oprah Winfrey",3
                );
        Question question4 = new Question(R.drawable.img_quote_4,
                "Insanely inspiring,\n" +
                        "insanely incorrect\n" +
                        "(maybe). Who is the\n" +
                        "true source of this\n" +
                        "inspiration?\n",
                "Nelson Mandela ",
                "Harriet Tubman",
                "Mahatma Gandhi",
                "Nicholas Klein",
                2);
        Question question5 = new Question(R.drawable.img_quote_5,
                "A peace worth striving\n" +
                        "for — who actually\n" +
                        "reminded us of this?",
                "Malala Yousafzai ",
                "Martin Luther King Jr. ",
                "Liu Xiaobo"
                , "Dalai Lama",
                1);

        questions.add(question0);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);

        totalCorrect = 0;
        totalQuestions = questions.size();

        Question firstQuestion = chooseNewQuestion();

        // TODO 3-D.ii: Uncomment the line below after implementing displayQuestionsRemaining(int)
           displayQuestionsRemaining(questions.size());

        // TODO 3-H.ii: Uncomment after implementing displayQuestion(Question)
         //displayQuestion(firstQuestion);
    }

    Question chooseNewQuestion() {
        int newQuestionIndex = generateRandomNumber(questions.size());
        currentQuestionIndex = newQuestionIndex;
        return questions.get(currentQuestionIndex);
    }

    int generateRandomNumber(int max) {
        double randomNumber = Math.random();
        double result = max * randomNumber;
        return (int) result;
    }

    Question getCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        return currentQuestion;
    }

    String getGameOverMessage(int totalCorrect, int totalQuestions) {
        if (totalCorrect == totalQuestions) {
            return "You got all " + totalQuestions + " right! You won!";
        } else {
            return "You got " + totalCorrect + " right out of " + totalQuestions + ". Better luck next time!";
        }
    }


}

