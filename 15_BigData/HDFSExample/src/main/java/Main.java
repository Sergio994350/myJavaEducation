import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;

public class Main {
    private static String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) throws Exception {


//        // реализовать все методы FileAccess:
//        // создаем экземпляр для работы с HDFS
//        FileAccess fileAccess = new FileAccess("hdfs://d022d9883721:8020");
//
//        // лист директорий и поддиректоррий
//        fileAccess.list(new Path("/")).forEach(System.out::println);
//
//        // read
//        System.out.println(fileAccess.read("test/file1.txt"));
//
//        // перезапись файла
//        fileAccess.ifExistDel("test/file.txt");
//
//        // запись файла в кластер
//        OutputStream os = fileAccess.createForWriting("test/file1.txt");
//        BufferedWriter br =
//        new BufferedWriter( new OutputStreamWriter(os, "UTF-8"));
//
//        // Генерация данных и запись в файл
//        for(int i = 0; i < 10_000; i++) {
//            br.write(getRandomWord() + " ");
//        }
//        br.flush();
//        br.close();
//
//        // закрываем поток
//        fileAccess.close();
//
//    }

        String filePathHDFS1 = "hdfs://d022d9883721:8020/test/file.txt";
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        System.setProperty("HADOOP_USER_NAME", "root");

        FileSystem hdfs = FileSystem.get(
                new URI("hdfs://d022d9883721:8020"), configuration
        );
        Path file = new Path("hdfs://d022d9883721:8020/test/file.txt");

        if (hdfs.exists(file)) {
            hdfs.delete(file, true);
        }

        OutputStream os = hdfs.create(file);
        BufferedWriter br = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8")
        );

//        for (int i = 0; i < 10_000; i++) {
//            br.write(getRandomWord() + " ");
            br.write(parseFile("C://data/hamlet.txt"));

    }

    private static String getRandomWord() {
        StringBuilder builder = new StringBuilder();
        int length = 2 + (int) Math.round(10 * Math.random());
        int symbolsCount = symbols.length();
        for (int i = 0; i < length; i++) {
            builder.append(symbols.charAt((int) (symbolsCount * Math.random())));
        }
        return builder.toString();
    }

    private static String parseFile(String filePath) {

        StringBuilder builder = new StringBuilder();
        try {
            FileInputStream fs = new FileInputStream(filePath);
            for (; ; ) {
                int code = fs.read();
                if (code < 0) {
                    break;
                }
                char ch = (char) code;
                builder.append(ch);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

}
