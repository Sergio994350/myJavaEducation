import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Students")
public class Student {
    //id
    //name
    //age
    //registration_date

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int age;

    @Column(name = "registration_date")
    private Date registrationDate;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) { this.id = id; }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

}