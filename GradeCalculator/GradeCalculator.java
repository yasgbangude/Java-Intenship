import javax.swing.*;
import java.awt.event.*;

public class GradeCalculator extends JFrame implements ActionListener {
    JLabel[] labels;
    JTextField[] textFields;
    JButton calculateButton;

    public GradeCalculator() {
        labels = new JLabel[5];
        textFields = new JTextField[5];
        String[] subjects = {"Subject 1", "Subject 2", "Subject 3", "Subject 4", "Subject 5"};
        for (int i = 0; i < 5; i++) {
            labels[i] = new JLabel(subjects[i]);
            textFields[i] = new JTextField();
            labels[i].setBounds(50, 50 + i * 50, 100, 30);
            textFields[i].setBounds(160, 50 + i * 50, 100, 30);
            add(labels[i]);
            add(textFields[i]);
        }
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(100, 300, 100, 30);
        calculateButton.addActionListener(this);
        add(calculateButton);
        setTitle("Student Grade Calculator");
        setSize(350, 400);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            int totalMarks = 0;
            int numSubjects = 0;

            for (int i = 0; i < 5; i++) {
                String marksText = textFields[i].getText();
                if (!marksText.isEmpty()) {
                    int marks = Integer.parseInt(marksText);
                    totalMarks += marks;
                    numSubjects++;
                }
            }
            double averagePercentage = (double) totalMarks / (numSubjects * 100) * 100;
            String grade = calculateGrade(averagePercentage);
            JOptionPane.showMessageDialog(this, "Total Marks: " + totalMarks +
                    "\nAverage Percentage: " + averagePercentage + "%" +
                    "\nGrade: " + grade);
        }
    }
    String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else if (percentage >= 35) {
            return "E";
        } else {
            return "F";
        }
    }
    public static void main(String[] args) {
        new GradeCalculator();
    }
}
