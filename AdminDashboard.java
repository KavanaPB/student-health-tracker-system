import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class AdminDashboard extends JFrame {
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;

    // Form fields
    private JTextField idField, nameField, heightField, weightField, bpSysField, bpDiaField, tempField, medField, allergyField;
    private JPasswordField passwordField;
    private JComboBox<String> genderCombo, bloodGroupCombo;
    private JSpinner ageSpinner;

    private final Font labelFont = new Font("Segoe UI", Font.BOLD, 18);
    private final Font fieldFont = new Font("Segoe UI", Font.PLAIN, 16);
    private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 18);

    public AdminDashboard() {
        setTitle("Admin Dashboard - Student Health System");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
        loadExistingStudents();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(20, 20, 20));
        header.setBorder(new EmptyBorder(15, 20, 15, 20));
        JLabel title = new JLabel("STUDENT HEALTH RECORDS ADMIN");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(180, 220, 255));
        JButton logoutBtn = new JButton("Logout");
        styleButton(logoutBtn, new Color(220, 53, 69)); // brighter red
        logoutBtn.setFont(buttonFont);
        logoutBtn.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
        header.add(title, BorderLayout.WEST);
        header.add(logoutBtn, BorderLayout.EAST);

        mainPanel.add(header, BorderLayout.NORTH);

        // Split pane for table and form
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(750);
        splitPane.setResizeWeight(0.7);
        splitPane.setBackground(new Color(30, 30, 30));

        // Table panel
        String[] columns = {"ID", "Name", "Age", "Gender", "Height(cm)", "Weight(kg)", "BMI", "BP", "Temp(°C)", "Blood Group", "Medications", "Allergies"};
        tableModel = new DefaultTableModel(columns, 0);
        studentTable = new JTable(tableModel);
        studentTable.setRowHeight(35);
        studentTable.setFont(fieldFont);
        studentTable.setBackground(new Color(40, 40, 40));
        studentTable.setForeground(Color.WHITE);
        studentTable.setGridColor(new Color(70, 70, 70));
        JScrollPane tableScroll = new JScrollPane(studentTable);
        tableScroll.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        splitPane.setLeftComponent(tableScroll);

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(25, 25, 25));
        formPanel.setBorder(new TitledBorder(new LineBorder(new Color(70, 130, 180), 3), "Add New Student Record", TitledBorder.LEFT, TitledBorder.TOP, labelFont, new Color(180, 220, 255)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        idField = createField(200);
        nameField = createField(200);
        ageSpinner = new JSpinner(new SpinnerNumberModel(18, 5, 100, 1));
        ((JSpinner.DefaultEditor) ageSpinner.getEditor()).getTextField().setFont(fieldFont);
        genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderCombo.setFont(fieldFont);
        heightField = createField(150);
        weightField = createField(150);
        bpSysField = createField(80);
        bpDiaField = createField(80);
        tempField = createField(100);
        bloodGroupCombo = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
        bloodGroupCombo.setFont(fieldFont);
        medField = createField(200);
        allergyField = createField(200);
        passwordField = createPasswordField(200);

        int row = 0;
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(createLabel("Student ID:"), gbc);
        gbc.gridx = 1; formPanel.add(idField, gbc);
        gbc.gridx = 2; formPanel.add(createLabel("Name:"), gbc);
        gbc.gridx = 3; formPanel.add(nameField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(createLabel("Age:"), gbc);
        gbc.gridx = 1; formPanel.add(ageSpinner, gbc);
        gbc.gridx = 2; formPanel.add(createLabel("Gender:"), gbc);
        gbc.gridx = 3; formPanel.add(genderCombo, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(createLabel("Height (cm):"), gbc);
        gbc.gridx = 1; formPanel.add(heightField, gbc);
        gbc.gridx = 2; formPanel.add(createLabel("Weight (kg):"), gbc);
        gbc.gridx = 3; formPanel.add(weightField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(createLabel("Blood Pressure (Sys):"), gbc);
        gbc.gridx = 1; formPanel.add(bpSysField, gbc);
        gbc.gridx = 2; formPanel.add(createLabel("Blood Pressure (Dia):"), gbc);
        gbc.gridx = 3; formPanel.add(bpDiaField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(createLabel("Body Temp (°C):"), gbc);
        gbc.gridx = 1; formPanel.add(tempField, gbc);
        gbc.gridx = 2; formPanel.add(createLabel("Blood Group:"), gbc);
        gbc.gridx = 3; formPanel.add(bloodGroupCombo, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(createLabel("Medications:"), gbc);
        gbc.gridx = 1; formPanel.add(medField, gbc);
        gbc.gridx = 2; formPanel.add(createLabel("Allergies:"), gbc);
        gbc.gridx = 3; formPanel.add(allergyField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(createLabel("Password:"), gbc);
        gbc.gridx = 1; formPanel.add(passwordField, gbc);

        row++;
        gbc.gridx = 3; gbc.gridy = row;
        JButton saveBtn = new JButton("Save Record");
        styleButton(saveBtn, new Color(0, 153, 255)); // brighter blue
        saveBtn.setFont(buttonFont);
        saveBtn.addActionListener(e -> saveRecord());
        formPanel.add(saveBtn, gbc);

        splitPane.setRightComponent(formPanel);

        mainPanel.add(splitPane, BorderLayout.CENTER);

        // Status bar
        statusLabel = new JLabel("Ready | Students: 0");
        statusLabel.setBorder(new EmptyBorder(5, 15, 5, 15));
        statusLabel.setBackground(new Color(60, 60, 60));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setOpaque(true);
        statusLabel.setFont(labelFont);
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JTextField createField(int width) {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(width, 32));
        field.setFont(fieldFont);
        field.setBackground(new Color(40, 40, 40));
        field.setForeground(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180)));
        return field;
    }

    private JPasswordField createPasswordField(int width) {
        JPasswordField field = new JPasswordField();
        field.setPreferredSize(new Dimension(width, 32));
        field.setFont(fieldFont);
        field.setBackground(new Color(40, 40, 40));
        field.setForeground(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180)));
        return field;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(labelFont);
        return label;
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(buttonFont);
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // more visible
    }

    private void saveRecord() {
        try {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            int age = (Integer) ageSpinner.getValue();
            String gender = (String) genderCombo.getSelectedItem();
            double height = Double.parseDouble(heightField.getText().trim());
            double weight = Double.parseDouble(weightField.getText().trim());
            int bpSys = Integer.parseInt(bpSysField.getText().trim());
            int bpDia = Integer.parseInt(bpDiaField.getText().trim());
            double temp = Double.parseDouble(tempField.getText().trim());
            String bloodGroup = (String) bloodGroupCombo.getSelectedItem();
            String meds = medField.getText().trim();
            String allergies = allergyField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (meds.equalsIgnoreCase("none")) meds = "";
            if (allergies.equalsIgnoreCase("none")) allergies = "";

            Student student = new Student(id, name, age, gender, password,
                                          height, weight, temp, bpSys, bpDia,
                                          bloodGroup, meds, allergies);

            DataStore.getInstance().addStudent(student);

            double bmi = weight / ((height / 100) * (height / 100));

            // Correct column order: height, weight, BMI
            tableModel.addRow(new Object[]{
                id, name, age, gender,
                height, weight,
                String.format("%.1f", bmi),
                bpSys + "/" + bpDia, temp, bloodGroup, meds, allergies
            });

            statusLabel.setText("Ready | Students: " + DataStore.getInstance().getAllStudents().size());

            // Clear fields
            idField.setText("");
            nameField.setText("");
            ageSpinner.setValue(18);
            genderCombo.setSelectedIndex(0);
            heightField.setText("");
            weightField.setText("");
            bpSysField.setText("");
            bpDiaField.setText("");
            tempField.setText("");
            bloodGroupCombo.setSelectedIndex(0);
            medField.setText("");
            allergyField.setText("");
            passwordField.setText("");

            JOptionPane.showMessageDialog(this, "Student record saved successfully!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadExistingStudents() {
        for (Student s : DataStore.getInstance().getAllStudents()) {
            double bmi = s.getWeight() / ((s.getHeight() / 100) * (s.getHeight() / 100));

            // Correct column order: height, weight, BMI
            tableModel.addRow(new Object[]{
                s.getId(), s.getName(), s.getAge(), s.getGender(),
                s.getHeight(), s.getWeight(),
                String.format("%.1f", bmi),
                s.getBpSystolic() + "/" + s.getBpDiastolic(),
                s.getTemperature(),
                s.getBloodGroup(), s.getMedications(), s.getAllergies()
            });
        }
        statusLabel.setText("Ready | Students: " + DataStore.getInstance().getAllStudents().size());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminDashboard().setVisible(true));
    }
}
