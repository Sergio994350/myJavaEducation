import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Reader {
    private static final String BANK_STATEMENT = "data/movementList.csv";
    private static final String TRANSACTIONS_OUT = "data/transactions.txt";
    private static final String CONTRAGENTS_INCOME = "data/contragentsIncome.txt";
    private static final String CONTRAGENTS_EXPENSE = "data/contragentsExpense.txt";
    public static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) throws ParseException {
        double totalIncome = 0;
        double totalExpense = 0;

        ArrayList<Transaction> transactions = loadInfoFromFile();
        TreeMap<String, Double> contragentsIncome = new TreeMap<>();
        TreeMap<String, Double> contragentsExpense = new TreeMap<>();
        try {
            PrintWriter writer1 = new PrintWriter(TRANSACTIONS_OUT);
//            PrintWriter writer2 = new PrintWriter("data/contragents.txt");

            for (int i = 0; i < transactions.size(); i++) {
                String cnName = transactions.get(i).getDescriptionOfTransaction().substring(20, 62);
                System.out.println("" + (i + 1) + "\t" + transactions.get(i).getDateOfTransaction() + "\t"
                        + transactions.get(i).getCodeOfTransaction() + "\t"
                        + cnName + "\t" + transactions.get(i).getIncome() + "\t"
                        + transactions.get(i).getExpense() + "\t");
                writer1.write("" + (i + 1) + "\t" + transactions.get(i).getDateOfTransaction() + "\t"
                        + transactions.get(i).getCodeOfTransaction() + "\t"
                        + cnName + "\t" + transactions.get(i).getIncome() + "\t"
                        + transactions.get(i).getExpense() + "\t" + "\n");

                if (transactions.get(i).getIncome() > 0) {
                    if (contragentsIncome.containsKey(cnName)) {
                        contragentsIncome.put(cnName, contragentsIncome.get(cnName) + transactions.get(i).getIncome());
                    } else {
                        contragentsIncome.put(cnName, transactions.get(i).getIncome());
                    }
                }
                if (transactions.get(i).getExpense() > 0) {
                    if (contragentsExpense.containsKey(cnName)) {
                        contragentsExpense.put(cnName, contragentsExpense.get(cnName) + transactions.get(i).getExpense());
                    } else {
                        contragentsExpense.put(cnName, transactions.get(i).getExpense());
                    }
                }

                totalExpense += transactions.get(i).getExpense();
                totalIncome += transactions.get(i).getIncome();
            }
            writer1.flush();
            writer1.close();

            System.out.println("Всего транзакций: " + transactions.size());
            System.out.println("Общий приход по счету: " + totalIncome);
            System.out.println("Общий расход по счету: " + totalExpense);

            System.out.println("Все приходы по счету по контрагентам: ");
            printMap(contragentsIncome);
            writeMap(contragentsIncome, CONTRAGENTS_INCOME);

            System.out.println("Все расходы по счету  по контрагентам: ");
            printMap(contragentsExpense);
            writeMap(contragentsExpense, CONTRAGENTS_EXPENSE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Тип счёта,Номер счета,Валюта,Дата операции,Референс проводки,Описание операции,Приход,Расход
    private static ArrayList<Transaction> loadInfoFromFile() {
        ArrayList<Transaction> bankTransactions = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(BANK_STATEMENT));
            for (String line : lines) {
                String[] fragments = line.split(",");
                if (fragments.length != 8) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                if (fragments[0].equalsIgnoreCase("Тип счёта")) {
                    continue;
                }
                bankTransactions.add(new Transaction(fragments[0], fragments[1], fragments[2],
                        (new SimpleDateFormat(dateFormat)).parse(fragments[3]),
                        fragments[4], fragments[5],
                        Double.parseDouble(fragments[6]), Double.parseDouble(fragments[7])));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bankTransactions;
    }

    private static void printMap(Map<String, Double> map) {
        for (String key : map.keySet()) {
            System.out.println(key + "\t" + map.get(key));
        }
    }

    private static void writeMap(Map<String, Double> map, String fileAddress) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileAddress);
        for (String key : map.keySet()) {
            writer.write(key + "\t" + map.get(key) + "\n");
        }
        writer.flush();
        writer.close();
    }
}