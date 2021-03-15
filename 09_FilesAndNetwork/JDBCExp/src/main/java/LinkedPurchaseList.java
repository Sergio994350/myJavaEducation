import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {

    //student_id
    //course_id

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "student_id")
    private Integer studentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedPurchaseList)) return false;
        LinkedPurchaseList that = (LinkedPurchaseList) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCourseId(), that.getCourseId()) &&
                Objects.equals(getStudentId(), that.getStudentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCourseId(), getStudentId());
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}