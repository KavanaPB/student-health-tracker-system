import javax.swing.*;
import javax.swing.border.*; // Add this line to import TitledBorder
import java.awt.*;

public class StudentDashboard extends JFrame {
    private Student student;

    private JLabel welcomeLabel;
    private JLabel bmiLabel;
    private JTextArea healthTipsArea;

    public StudentDashboard(Student student) {
        this.student = student;
        initUI();
    }

    private void initUI() {
        setTitle("Student Dashboard - " + student.getName());
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(30, 30, 30));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        welcomeLabel = new JLabel("Welcome, " + student.getName() + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setForeground(Color.WHITE);
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        centerPanel.setBackground(new Color(30, 30, 30));

        // Health Status Panel
        JPanel healthPanel = new JPanel(new BorderLayout(10, 10));
        healthPanel.setBackground(new Color(40, 40, 40));
        healthPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                "Your Health Status",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 20),
                new Color(180, 220, 255)
        ));

        bmiLabel = new JLabel();
        bmiLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        bmiLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bmiLabel.setForeground(Color.WHITE);
        healthPanel.add(bmiLabel, BorderLayout.NORTH);

        JTextArea recordArea = new JTextArea();
        recordArea.setEditable(false);
        recordArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        recordArea.setBackground(new Color(50, 50, 50));
        recordArea.setForeground(Color.WHITE);
        recordArea.setText(getHealthRecordText());
        healthPanel.add(new JScrollPane(recordArea), BorderLayout.CENTER);

        centerPanel.add(healthPanel);

        // Health Tips Panel
        JPanel tipsPanel = new JPanel(new BorderLayout(10, 10));
        tipsPanel.setBackground(new Color(40, 40, 40));
        tipsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                "Personalized Health Tips & Advice",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 20),
                new Color(180, 220, 255)
        ));

        healthTipsArea = new JTextArea();
        healthTipsArea.setEditable(false);
        healthTipsArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        healthTipsArea.setBackground(new Color(50, 50, 50));
        healthTipsArea.setForeground(Color.WHITE);
        healthTipsArea.setLineWrap(true);
        healthTipsArea.setWrapStyleWord(true);
        healthTipsArea.setText(generateHealthTips());

        tipsPanel.add(new JScrollPane(healthTipsArea), BorderLayout.CENTER);

        centerPanel.add(tipsPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);

        updateBMILabel();
    }

    private void updateBMILabel() {
        double bmi = student.getWeight() / ((student.getHeight() / 100) * (student.getHeight() / 100));
        String category;
        Color color;

        if (bmi < 18.5) {
            category = "Underweight";
            color = Color.ORANGE;
        } else if (bmi < 25) {
            category = "Normal";
            color = Color.GREEN;
        } else if (bmi < 30) {
            category = "Overweight";
            color = Color.YELLOW;
        } else {
            category = "Obese";
            color = Color.RED;
        }

        bmiLabel.setText(String.format("<html><div style='text-align:center;'>BMI: %.1f<br><span style='color:rgb(%d,%d,%d); font-size:28px;'>%s</span></div></html>",
                bmi, color.getRed(), color.getGreen(), color.getBlue(), category));
    }

    private String getHealthRecordText() {
        return String.format(
                "ID: %s\nName: %s\nAge: %d\nGender: %s\nHeight: %.1f cm\nWeight: %.1f kg\n" +
                        "Blood Pressure: %d/%d\nTemperature: %.1f °C\nBlood Group: %s\nMedications: %s\nAllergies: %s",
                student.getId(), student.getName(), student.getAge(), student.getGender(),
                student.getHeight(), student.getWeight(),
                student.getBpSystolic(), student.getBpDiastolic(), student.getTemperature(),
                student.getBloodGroup(),
                student.getMedications().isEmpty() ? "None" : student.getMedications(),
                student.getAllergies().isEmpty() ? "None" : student.getAllergies()
        );
    }

    private String generateHealthTips() {
        StringBuilder tips = new StringBuilder();

        double bmi = student.getWeight() / ((student.getHeight() / 100) * (student.getHeight() / 100));
        int sys = student.getBpSystolic();
        int dia = student.getBpDiastolic();
        double temp = student.getTemperature();

        if (bmi < 18.5) {
            tips.append("- Your BMI indicates you are underweight. Consider a nutrient-rich diet and consult a nutritionist.\n");
        } else if (bmi < 25) {
            tips.append("- Your BMI is normal. Maintain a balanced diet and regular exercise.\n");
        } else if (bmi < 30) {
            tips.append("- Your BMI indicates overweight. Increase physical activity and monitor your diet.\n");
        } else {
            tips.append("- Your BMI indicates obesity. Consult a healthcare provider for a personalized plan.\n");
        }

        if (sys > 130 || dia > 85) {
            tips.append("- Your blood pressure is elevated. Reduce salt intake and manage stress.\n");
        } else {
            tips.append("- Your blood pressure is within normal range. Keep up the healthy lifestyle.\n");
        }

        if (temp > 37.5) {
            tips.append("- You have a slightly elevated body temperature. Monitor for fever symptoms.\n");
        }

        if (!student.getMedications().isEmpty()) {
            tips.append("- Remember to take your medications as prescribed.\n");
        }
        if (!student.getAllergies().isEmpty()) {
            tips.append("- Avoid allergens: ").append(student.getAllergies()).append(".\n");
        }

        tips.append("- Drink plenty of water daily.\n");
        tips.append("- Aim for 7-8 hours of sleep each night.\n");
        tips.append("- Engage in at least 30 minutes of moderate exercise most days.\n");

        return tips.toString();
    }
}
