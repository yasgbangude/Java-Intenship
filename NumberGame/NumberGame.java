import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGame{
    JFrame frame;
    JLabel label;
    JTextField textField;
    JButton submitButton;
    int targetNumber;
    int attempts;
    int maxAttempts;
    int score;

    public NumberGame() {
        frame = new JFrame("Number Game");
        label = new JLabel("Enter your guess:");
        textField = new JTextField(10);
        submitButton = new JButton("Submit");
        targetNumber = generateRandomNumber();
        maxAttempts = 15;
        attempts = 0;
        score = 0;

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        frame.setLayout(new FlowLayout());
        frame.add(label);
        frame.add(textField);
        frame.add(submitButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setVisible(true);
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    private void checkGuess() {
        int userGuess = Integer.parseInt(textField.getText());
        attempts++;

        if (userGuess == targetNumber) {
            JOptionPane.showMessageDialog(frame, "Congratulations! You guessed the correct number.");
            score++;
        } else if (attempts < maxAttempts) {
            String message = (userGuess < targetNumber) ? "Too low! Try a higher number." : "Too high! Try a lower number.";
            JOptionPane.showMessageDialog(frame, message);
        } else {
            JOptionPane.showMessageDialog(frame, "Sorry! You've used all your attempts. The correct number was " + targetNumber);
        }

        if (attempts < maxAttempts) {
            targetNumber = generateRandomNumber();
            textField.setText("");
        } else {
            showScore();
        }
    }
    void showScore() {
        JOptionPane.showMessageDialog(frame, "Your final score is: " + score);
        frame.dispose();
    }
    public static void main(String[] args) {
        new NumberGame();
    }
}
