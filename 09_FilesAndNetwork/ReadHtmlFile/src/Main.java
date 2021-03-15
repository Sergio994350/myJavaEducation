import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

    public class Main {
        public static void main(String[] args) throws IOException {
            Document page = Jsoup.connect("https://lenta.ru/").get();
            Elements elements = page.select("img.g-picture");
            ArrayList<String> imgUrls = new ArrayList<>();
            elements.forEach(element -> imgUrls.add(element.attr("abs:src")));

            downloadImgs(imgUrls, getOutputPath());

        }

        public static String getOutputPath() {
            JFileChooser outputDirectoryChooser = new JFileChooser();
            outputDirectoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            outputDirectoryChooser
                    .showDialog(null, "Choose output directory");
            File outputDirectory = outputDirectoryChooser.getSelectedFile();

            return outputDirectory.getPath();
        }

        public static String getImageName (String imageUrl) {
            int lastSlashIndex =  imageUrl.lastIndexOf("/");
            return imageUrl.substring(lastSlashIndex + 1, imageUrl.length());
        }

        public static void downloadImgs (ArrayList<String> imgUrls, String outputPath) {
            imgUrls.forEach(s ->
            {
                try {
                    FileUtils
                            .copyURLToFile(new URL(s),
                                    Paths.get(outputPath + "\\" + getImageName(s)).toFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }