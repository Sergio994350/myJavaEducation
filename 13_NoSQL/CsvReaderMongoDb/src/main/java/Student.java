import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.List;

public class Student extends BasicDBObject{

    String name;
    String surName;
    int age;
    String courses;

    public Student() {
    }

    public Student(String name, String surName, int age, String courses) {
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", age=" + age +
                ", courses='" + courses + '\'' +
                '}';
    }

    public BasicDBObject toDBObject() {
        BasicDBObject document = new BasicDBObject();

        document.put("name", name);
        document.put("surName", surName);
        document.put("age", age);
        document.put("courses", courses);

        return document;
    }

    public static Student fromDBObject(DBObject document) {
        Student b = new Student();

        b.name = (String) document.get("name");
        b.surName = (String) document.get("surName");
        b.age = (int) document.get("age");
        b.courses = (String) document.get("courses");

        return b;
    }

}
