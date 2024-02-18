import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApplication extends JFrame {
    JLabel questionLabel;
    JRadioButton[] options;
    JButton submitButton;
    JLabel timerLabel;
    int currentQuestion;
    int score;
    Timer timer;

    String[] questions = {
            "Which of the following option leads to the portability and security of Java?",
            "Which of the following is not a Java features?",
            "_____ is used to find and fix bugs in the Java programs."
    };
    String[][] choices = {
            {"Bytecode is executed by JVM","The applet makes the Java code secure and portable","Use of exception handling","Dynamic binding between objects"},
            {"Dynamic","Architecture Neutral","Use of pointers","Object-oriented"},
            {"JVM","JRE","JDK","JDB"}
    };
    int [] correctAnswers = {1, 3, 4};

    public QuizApplication() {
        setTitle("Quiz Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 400);
        setLayout(new BorderLayout());

        questionLabel = new JLabel();
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        options = new JRadioButton[4];
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setText(choices[currentQuestion][i]);
            buttonGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        add(submitButton, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                showNextQuestion();
            }
        });

        timerLabel = new JLabel();
        add(timerLabel, BorderLayout.EAST);

        currentQuestion=0;
        showQuestion();
        startTimer();
    }

    private void showQuestion() {
        questionLabel.setText(questions[currentQuestion]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(choices[currentQuestion][i]);
            options[i].setSelected(false);
        }
    }

    private void showNextQuestion() {
        currentQuestion++;
        if (currentQuestion < questions.length) {
            showQuestion();
            resetTimer();
        } else {
            showResult();
        }
    }

    private void checkAnswer() {
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected() && i == correctAnswers[currentQuestion]) {
                score++;
            }
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Quiz Completed!\nScore: " + score + "/" + questions.length);
        System.exit(0);
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            int secondsLeft = 30;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondsLeft >= 0) {
                    timerLabel.setText("Time left: " + secondsLeft + " seconds");
                    secondsLeft--;
                } else {
                    timer.stop();
                    checkAnswer();
                    showNextQuestion();
                }
            }
        });
        timer.start();
    }

    private void resetTimer() {
        timer.stop();
        startTimer();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new QuizApplication().setVisible(true);
            }
        });
    }
}
