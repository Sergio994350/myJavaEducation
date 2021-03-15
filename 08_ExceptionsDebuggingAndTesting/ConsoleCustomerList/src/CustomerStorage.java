import java.util.HashMap;

public class CustomerStorage extends CsExceptions
{
    public HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {
        String[] components = data.split("\\s+");
        if ((components.length !=4) || (!components[2].contains("@")) || (components[3].charAt(1) != '7'
            || components[3].length() !=12)) {
            CsExceptions.checkExceptions(data);
    }
        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }
}