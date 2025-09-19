import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class LoginFrame extends JFrame {
    private JRadioButton adminRadio, studentRadio;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton submitBtn;

    public LoginFrame() {
        setTitle("Student Health System Login");
        setSize(400, 480);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 400, 480, 30, 30));
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(20, 25, 35),
                        getWidth(), getHeight(), new Color(40, 45, 55));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel title = new JLabel("STUDENT HEALTH SYSTEM");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(180, 220, 255));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        mainPanel.add(title);

        adminRadio = new JRadioButton("Admin Login");
        studentRadio = new JRadioButton("Student Login");
        adminRadio.setForeground(Color.WHITE);
        studentRadio.setForeground(Color.WHITE);
        adminRadio.setBackground(new Color(20, 25, 35));
        studentRadio.setBackground(new Color(20, 25, 35));
        adminRadio.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(adminRadio);
        group.add(studentRadio);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        radioPanel.setOpaque(false);
        radioPanel.add(adminRadio);
        radioPanel.add(studentRadio);
        mainPanel.add(radioPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        usernameField = (JTextField) createField("Username", 250);
        new TextPrompt("Username", usernameField);
        mainPanel.add(usernameField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        passwordField = (JPasswordField) createField("Password", 250);
        new TextPrompt("Password", passwordField);
        mainPanel.add(passwordField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        submitBtn = new JButton("Submit");
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.setPreferredSize(new Dimension(250, 40));
        submitBtn.setBackground(new Color(70, 130, 180));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        submitBtn.setFocusPainted(false);
        submitBtn.addActionListener(e -> handleLogin());
        mainPanel.add(submitBtn);

        add(mainPanel);
    }

    private JComponent createField(String placeholder, int width) {
        JComponent field;
        if (placeholder.equals("Password")) {
            field = new JPasswordField();
        } else {
            field = new JTextField();
        }
        field.setMaximumSize(new Dimension(width, 35));
        field.setPreferredSize(new Dimension(width, 35));
        field.setOpaque(true);
        field.setBackground(new Color(40, 45, 55));
        field.setForeground(Color.WHITE);
        if (field instanceof JTextField) {
            ((JTextField) field).setCaretColor(Color.WHITE);
        } else if (field instanceof JPasswordField) {
            ((JPasswordField) field).setCaretColor(Color.WHITE);
        }
        ((JComponent) field).setToolTipText(placeholder);
        return field;
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (adminRadio.isSelected()) {
            if (username.equals("admin") && password.equals("admin123")) {
                new AdminDashboard().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid admin credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (DataStore.getInstance().validateStudentLogin(username, password)) {
                Student student = DataStore.getInstance().getStudent(username);
                new StudentDashboard(student).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid student credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}



