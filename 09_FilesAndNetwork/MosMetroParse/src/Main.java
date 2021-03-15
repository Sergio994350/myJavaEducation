import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.simple.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        String htmlFile = parseFile("data/code.html");
        String filetoWrite = "data/file.json";
        Document doc = Jsoup.parse(htmlFile);

        //для парсинга самого сайта будет такой вариант:
//        String webSiteURL = "https://www.moscowmap.ru/metro.html#lines";
//        Document doc = null;
//        try {
//            doc = Jsoup.connect(webSiteURL).get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        // выводим номера линий и список линий
        Elements elementsLines = doc.select("span.js-metro-line");
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<String> lineNumbers = new ArrayList<>();
        ArrayList<String> lineCount = new ArrayList<>();
        for (Element e : elementsLines) {
            lines.add(e.text());
        }
        for (Element e : elementsLines) {
            lineNumbers.add(e.attr("data-line"));
        }

// записали элемент:
//<span class="js-metro-line t-metrostation-list-header t-icon-metroln ln-1"
// data-line="1">Сокольническая линия</span>

        // выводим номера станций и список станций
        Elements elementsStations = doc.select("div.js-metro-stations");
        ArrayList<String> stations = new ArrayList<>();
        elementsStations.forEach(element -> stations.add(element.text()));

        // посчитаем кол-во станций на каждой линии
        for (int i = 0; i < stations.size(); i++) {
            long stationsCount = stations.get(i).chars().filter(ch -> ch == '.').count();
            lineCount.add("" + stationsCount);
            System.out.println(lines.get(i) + " (Количество станций на линии: " + stationsCount + ")");
        }

// записали элемент:
//<p><a data-metrost="243,259,0,5,302,0" href="/metro/zamoskvoretskaya-linija/hovrino.html">
// <span class="num">1.&nbsp;&nbsp;</span><span class="name">Ховрино</span></a></p>

        //запишем данные в файл (вариант2)
        try {
            FileWriter file = new FileWriter(filetoWrite);
            file.write("{\n\"" + "Lines\" : " + "[ " + "\n");
            for (int i = 0; i < lines.size(); i++) {
                JSONObject object1 = new JSONObject();
                object1.put("name", String.valueOf(lines.get(i)));
                object1.put("number", String.valueOf(lineNumbers.get(i)));
                if (i != lines.size() - 1) {
                    file.write(object1.toJSONString() + "," + "\n");
                } else {
                    file.write(object1.toJSONString() + "],\n" + "  \"Stations\" : [\n");
                }
            }
            for (int j = 0; j < lines.size(); j++) {
                JSONObject object2 = new JSONObject();
                object2.put(String.valueOf(lineNumbers.get(j)), String.valueOf(stations.get(j)));
                if (j != lines.size() - 1) {
                    file.write(object2.toJSONString() + "," + "\n");
                } else file.write(object2.toJSONString() + "\n]\n}");
            }
            file.flush();
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // read JSON var2
        try {
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(filetoWrite));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode parser = objectMapper.readTree(reader);

            // read JSON file
            for (JsonNode project : parser.path("name")) {
                System.out.println(project.path("number").asText());
                System.out.println(parser.get(String.valueOf(lineNumbers)).asText());
            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String parseFile(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append(line + "\n"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}