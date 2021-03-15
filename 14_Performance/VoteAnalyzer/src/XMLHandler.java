import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler
{
    int limit = 500_000;
    int number = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try
        {
            if (qName.equals("voter") && number < limit)
            {
                String birthDay = attributes.getValue("birthDay").replace('.', '-');
                String name = attributes.getValue("name");
                DBConnection.countVoter(name,birthDay,number);
                number++;
                if (number % 100000 == 0) {
                    System.out.printf("################## %d * Part: %.3f sec * Total: %.1f sec ##################%n"
                            ,number,(double)(System.currentTimeMillis()-Loader.timer)/1000,
                            (double)(System.currentTimeMillis()-Loader.total_timer)/1000);
                    Loader.timer = System.currentTimeMillis();
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

}