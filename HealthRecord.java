import java.io.Serializable;

public class HealthRecord implements Serializable {
    public String studentId;
    public double height; // in cm
    public double weight; // in kg
    public double temperature;
    public int bpSystolic;
    public int bpDiastolic;
    public String bloodGroup;
    public String medications;
    public String allergies;

    public HealthRecord(String studentId, double height, double weight, 
                        double temperature, int bpSystolic, int bpDiastolic,
                        String bloodGroup, String medications, String allergies) {
        this.studentId = studentId;
        this.height = height;
        this.weight = weight;
        this.temperature = temperature;
        this.bpSystolic = bpSystolic;
        this.bpDiastolic = bpDiastolic;
        this.bloodGroup = bloodGroup;
        this.medications = medications;
        this.allergies = allergies;
    }
}
