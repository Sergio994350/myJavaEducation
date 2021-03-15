import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter site address: example - > https://mail.ru ");
        String url = sc.nextLine();

        System.out
                .println("Enter number of threads: - > 0 is default.");
        int numThreads = sc.nextInt();

        System.out.println("Scanning the site...");
        long start = System.currentTimeMillis();
        LinkExecutor linkExecutor = new LinkExecutor(url, url);
        String siteMap = numThreads == 0 ? new ForkJoinPool().invoke(linkExecutor)
                : new ForkJoinPool(numThreads).invoke(linkExecutor);

        System.out.println("Scanning is finished.");
        System.out
                .println("Time of scanning " + ((System.currentTimeMillis() - start) / 1000) + " seconds.");
        writeFiles(siteMap);
    }

    private static void writeFiles(String map) {
        System.out.println("Write the file...");
        String filePath = "siteMap.txt";

        File file = new File(filePath);
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Site Map is completed!");
    }
}