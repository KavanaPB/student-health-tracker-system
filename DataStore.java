import java.util.*;

public class DataStore {
    private static DataStore instance = null;

    private Map<String, Student> students = new HashMap<>();

    private DataStore() {}

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public void addStudent(Student s) {
        students.put(s.getId(), s);
    }

    public Student getStudent(String id) {
        return students.get(id);
    }

    public boolean validateStudentLogin(String id, String password) {
        Student s = students.get(id);
        return s != null && s.getPassword().equals(password);
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }
}
