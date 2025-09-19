import java.io.Serializable;

public class Doctor implements Serializable {
    public String id;
    public String name;
    public String specialty;
    public String phone;
    public String email;
    
    public Doctor(String id, String name, String specialty, String phone, String email) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.phone = phone;
        this.email = email;
    }
}


