import java.io.*;
import java.net.URL;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DownloadImages {

    private static final String webSiteURL = "https://lenta.ru/";

    private static final String folderPath = "D:/target/";

    public static void main(String[] args) throws Exception {
        try {
            Document doc = Jsoup.connect(webSiteURL).get();
            Elements img = doc.getElementsByTag("img");

            for (Element el : img) {

                //for each element get the srs url
                String src = el.absUrl("src");

                System.out.println("Image Found!");
                System.out.println("src attribute is : " + src);
                getImages(src);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private static void getImages(String src) throws Exception {

        String folder = null;
        try {

            //Exctract the name of the image from the src attribute
            int indexname = src.lastIndexOf("/");

            if (indexname == src.length()) {
                src = src.substring(1, indexname);
            }

            indexname = src.lastIndexOf("/");
            String name = src.substring(indexname, src.length());

            System.out.println(name);

            //Open a URL Stream
            URL url = new URL(src);
            InputStream in = url.openStream();
            try {
                OutputStream out = new BufferedOutputStream(new FileOutputStream(folderPath + name));
                for (int b; (b = in.read()) != -1; ) {
                    out.write(b);
                }
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}