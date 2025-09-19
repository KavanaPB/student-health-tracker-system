import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BMICalculator extends JFrame {
    private JTextField heightField;
    private JTextField weightField;
    private JLabel resultLabel;

    public BMICalculator() {
        setTitle("BMI Calculator");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel("BMI CALCULATOR", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(title, gbc);

        // Height Input
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        panel.add(new JLabel("Height (m):"), gbc);
        heightField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(heightField, gbc);

        // Weight Input
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Weight (kg):"), gbc);
        weightField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(weightField, gbc);

        // Calculate Button
        JButton calculateBtn = new JButton("Calculate BMI");
        calculateBtn.setBackground(new Color(0, 120, 215));
        calculateBtn.setForeground(Color.WHITE);
        calculateBtn.addActionListener(e -> calculateBMI());
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(calculateBtn, gbc);

        // Result Display
        resultLabel = new JLabel(" ", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(resultLabel, gbc);

        add(panel);
    }

    private void calculateBMI() {
        try {
            double height = Double.parseDouble(heightField.getText());
            double weight = Double.parseDouble(weightField.getText());
            
            double bmi = weight / (height * height);
            String category = getBMICategory(bmi);
            Color color = getBMIColor(bmi);
            
            resultLabel.setForeground(color);
            resultLabel.setText(String.format("BMI: %.1f (%s)", bmi, category));
        } catch (NumberFormatException ex) {
            resultLabel.setForeground(Color.RED);
            resultLabel.setText("Invalid input!");
        }
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi < 25) return "Normal";
        if (bmi < 30) return "Overweight";
        return "Obese";
    }

    private Color getBMIColor(double bmi) {
        if (bmi < 18.5) return new Color(255, 184, 77);  // Orange
        if (bmi < 25) return new Color(76, 175, 80);     // Green
        if (bmi < 30) return new Color(255, 152, 0);     // Orange
        return new Color(244, 67, 54);                   // Red
    }
}
