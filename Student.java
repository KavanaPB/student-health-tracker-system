public class Student {
    private String id, name, gender, password;
    private int age;
    private double height, weight, temperature;
    private int bpSystolic, bpDiastolic;
    private String bloodGroup, medications, allergies;

    public Student(String id, String name, int age, String gender, String password,
                   double height, double weight, double temperature,
                   int bpSystolic, int bpDiastolic, String bloodGroup,
                   String medications, String allergies) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.temperature = temperature;
        this.bpSystolic = bpSystolic;
        this.bpDiastolic = bpDiastolic;
        this.bloodGroup = bloodGroup;
        this.medications = medications;
        this.allergies = allergies;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getPassword() { return password; }
    public double getHeight() { return height; }
    public double getWeight() { return weight; }
    public double getTemperature() { return temperature; }
    public int getBpSystolic() { return bpSystolic; }
    public int getBpDiastolic() { return bpDiastolic; }
    public String getBloodGroup() { return bloodGroup; }
    public String getMedications() { return medications; }
    public String getAllergies() { return allergies; }
}





