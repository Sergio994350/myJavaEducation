import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
//при обработке исключений использовать catch (IllegalArgumentException | IOException
// | NullPointerException e) { ... В решении примените оператор throw.
public class Main {
    public static void main (String[] args) {
        long fileSize = 0;
        for (; ; ) {
        System.out.println("Пожалуйста, введите полный путь к файлу в формате C:/dir/file.txt " +
                "\n0 - для выхода из программы");
        String filePathUser = new Scanner(System.in).nextLine();
            if (filePathUser.equals("0")) {
                break;
            }
            if (filePathUser.equalsIgnoreCase("")) {
                try {
                    throw new Exception("String can not be empty!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (Files.isDirectory(Paths.get(filePathUser))) {
                try {
                    fileSize = PathSize.calculateSize(Paths.get(filePathUser));
                } catch (IllegalArgumentException | NullPointerException ex) {
                    ex.printStackTrace();
                }
                System.out.print("Размер директории равен ");
                checkFileSize(fileSize);
                break;
            }
            if (Files.isRegularFile(Paths.get(filePathUser))) {
                System.out.print("Размер файла равен ");
                try {
                    fileSize = Files.size(Paths.get(filePathUser));
                } catch (IllegalArgumentException | IOException | NullPointerException ex) {
                    ex.printStackTrace();
                }
                checkFileSize(fileSize);
                break;
            } else {
                System.out.println("Ошибочный ввод.");
            }
        }
    }

    public static void checkFileSize(Long size) {
        int sizeCase = 0;
        if (size < 1024) {
            System.out.print("" + size + " байт");
        }
        if (size >= 1024 && size < 1048576) {
            System.out.print("" + size / 1024 + " килобайт");
        }
        if (size >= 1048576 && size < 1073741824) {
            System.out.print("" + size / 1048576 + " мегабайт");
        }
        if (size >= 1073741824) {
            System.out.print("" + size / 1073741824 + " гигабайт");
        }
    }
}