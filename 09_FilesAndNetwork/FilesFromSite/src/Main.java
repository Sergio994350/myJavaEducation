import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

public class Main {
    public static void main(String[] args) throws IOException {
        Document page = Jsoup.connect("https://lenta.ru/").get();
        Elements elements = page.select("img.g-picture");
        ArrayList<String> picAddress = new ArrayList<>();
        elements.forEach(element -> picAddress.add(element.attr("abs:src")));
        getPics(picAddress, getOutputPath());
    }

    public static String getOutputPath() {
        JFileChooser outputDirectoryChooser = new JFileChooser();
        outputDirectoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        outputDirectoryChooser
                .showDialog(null, "Choose target directory");
        File outputDirectory = outputDirectoryChooser.getSelectedFile();
        return outputDirectory.getPath();
    }

    public static String getImageName (String imageUrl) {
        int lastSlashIndex =  imageUrl.lastIndexOf("/");
        return imageUrl.substring(lastSlashIndex + 1, imageUrl.length());
    }

    public static void getPics (ArrayList<String> imgUrls, String outputPath) {
        imgUrls.forEach(s ->
        {
            try {
                FileUtils.copyURLToFile(new URL(s),
                                Paths.get(outputPath + "\\" + getImageName(s)).toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}