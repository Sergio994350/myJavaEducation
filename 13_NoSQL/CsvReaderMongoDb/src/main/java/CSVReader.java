import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//Напишите программу, которая получает на вход csv файл
// с данными о студентах: имя, возраст и список курсов через запятую,
// и вставляет эти данные в MongoDB. (csv файл приложен к этому уроку)


public class CSVReader {

    public static void main(String[] args) {

        String csvFile = "D:/source/mongo.csv";
        ArrayList<Student> studentListArray = readCSV(csvFile);

        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("test");
//        mongoClient.getDatabase("test").getCollection("studentlist");
        MongoCollection<Document> studentlistCollection = database.getCollection("studentlist");
        Document document = new Document();
        // очистим коллекцию
        studentlistCollection.deleteMany(document);

        // запишем данные в Mongo DB
        for (int i = 0; i < studentListArray.size(); i++) {
            document = new Document();
            Student student = studentListArray.get(i);
            document.put("name", student.getName());
            document.put("surName", student.getSurName());
            document.put("age", student.getAge());
            document.put("courses", student.getCourses());

            studentlistCollection.insertOne(document);
        }

//После этого программа должна получить из БД и вывести на экран:
//— общее количество студентов в базе.
//— количество студентов старше 40 лет.
//— имя самого молодого студента.
//— список курсов самого старого студента.

        int forties = 0;
        int youngIndex = 0;
        int oldestIndex = 0;
        String coursesOldest = "";

        for (int i = 0; i < studentlistCollection.countDocuments(); i++) {
            int age2 = studentListArray.get(i).getAge();
            if (age2 > 40) {
                forties++;
                if (age2 > studentListArray.get(oldestIndex).getAge()) {
                    oldestIndex = i;
                }
            }
            if (age2 < studentListArray.get(youngIndex).getAge()) {
                youngIndex = i;
            }
        }
        coursesOldest = studentListArray.get(oldestIndex).getCourses();

        System.out.println("Студентов в базе: " + studentlistCollection.countDocuments());
        System.out.println("Количество студентов старше 40 лет: " + forties);
        System.out.println("Имя самого молодого студента: "
                + studentListArray.get(youngIndex).getName() + " "
                + studentListArray.get(youngIndex).getSurName());
        System.out.println("Самый возрастной студент: "
                + studentListArray.get(oldestIndex).getName() + " "
                + studentListArray.get(oldestIndex).getSurName());
        System.out.println("Cписок курсов самого возрастного студента: " + coursesOldest);

        mongoClient.close();

    }


    public static ArrayList<Student> readCSV(String csvFilePath) {
        ArrayList<Student> studentList = new ArrayList<>();
        BufferedReader br = null;
        String line = "";
        int number = 0;

        try {
            br = new BufferedReader(new FileReader(csvFilePath));
            while ((line = br.readLine()) != null) {

                //делим строку по символу кавычек
                String[] line1 = line.split("(\")");

                // используем запятую и пробел в качестве разделителя
                String[] studentMask = line1[0].split("[\\s\\,]+");

                Student student =
                        new Student(studentMask[0], studentMask[1],
                                Integer.parseInt(studentMask[2]), line1[1]);
                studentList.add(number, student);
                number++;

                System.out.println("Student [name: " + student.getName() + " surname: " + student.getSurName() +
                        " age: " + student.getAge() + " courses: " + student.getCourses() + " ]");

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return studentList;
    }
}