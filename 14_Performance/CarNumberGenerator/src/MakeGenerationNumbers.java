import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MakeGenerationNumbers extends Thread {
    private String dstFolder;
    private long start;
    private int regionFrom;
    private int regionTo;
    private int numberFrom;
    private int numberTo;

    public MakeGenerationNumbers(String dstFolder, long start, int regionFrom,
                                 int regionTo, int numberFrom, int numberTo) {
        this.dstFolder = dstFolder;
        this.start = start;
        this.regionFrom = regionFrom;
        this.regionTo = regionTo;
        this.numberFrom = numberFrom;
        this.numberTo = numberTo;
    }

    @Override
    public void run() {
        try {
            PrintWriter writer = new PrintWriter(dstFolder);
            char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            for (int regionCode = regionFrom; regionCode < regionTo; regionCode++) {
                StringBuilder builder = new StringBuilder();
                for (int number = numberFrom; number < numberTo; number++) {
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                builder.append(firstLetter);
                                builder.append(padNumber(number, 3));
                                builder.append(secondLetter);
                                builder.append(thirdLetter);
                                builder.append(padNumber(regionCode, 2));
                                builder.append("\n");
                            }
                        }
                    }
                }
                writer.write(builder.toString());
            }
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Finished after start: " + (System.currentTimeMillis() - start) + " ms");
    }

    private String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
//            StringBuilder builder1 = new StringBuilder();
        for (int i = 0; i < padSize; i++) {
//                builder1.append('0' + numberStr);
            numberStr = '0' + numberStr;
        }
//            numberStr = builder1.toString();
        return numberStr;
    }
}