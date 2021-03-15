public class Main {
    public static void main(String[] args) {
        int first = 65; // задаем код первой буквы алфавита
        int last = 90;  // задаем код последней буквы алфавита

        for (int letterCode = first; letterCode <= last ; letterCode++) {
            char letter = (char) letterCode;
            System.out.println("Буква " + letter + " : код буквы " + letterCode);
        }
    }
}
