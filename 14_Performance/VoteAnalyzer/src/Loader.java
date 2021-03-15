import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.sql.SQLException;

public class Loader
{
    public static long timer = System.currentTimeMillis();
    public static long total_timer = System.currentTimeMillis();

    public static void main(String[] args) throws SQLException {
        String fileName = "res/data-1572M.xml";
        long start = System.currentTimeMillis();
        try
        {
            parseFileSax(fileName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // Печать используемой памяти
        long usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.printf("Used memory: %,.2f Mb%nParsing time: %,.3f sec.%n"
                , (double) usage / Math.pow(2, 20),(double) (System.currentTimeMillis() - start)/1000);
        System.out.println("################## < Find Duplicates: > ##################");
        DBConnection.printVoterCounts();
        System.out.println("################## < Let's Find Person by Name: > ##################");
        DBConnection.customSelect("Щаников Люксен");
        DBConnection.connectionClose();
    }

    private static void parseFileSax(String fileName) throws Exception
    {
        DBConnection.getConnection();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName),handler);
        System.out.println("Parsing completed");
        DBConnection.executeBatch();

    }
}