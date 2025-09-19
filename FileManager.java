import java.io.*;
import java.util.HashMap;

public class FileManager {
    private static final String STUDENT_FILE = "students.dat";
    private static final String RECORD_FILE = "records.dat";
    private static final String DOCTOR_FILE = "doctors.dat";
    
    public static void saveStudents(HashMap<String, Student> students) throws IOException {
        saveData(STUDENT_FILE, students);
    }
    
    public static HashMap<String, Student> loadStudents() throws IOException, ClassNotFoundException {
        return (HashMap<String, Student>) loadData(STUDENT_FILE);
    }
    
    // Similar save/load methods for HealthRecord and Doctor
    
    private static void saveData(String filename, Object data) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(data);
        }
    }
    
    private static Object loadData(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return ois.readObject();
        }
    }
}
