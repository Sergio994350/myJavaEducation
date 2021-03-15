public class Main {
    public static void main(String[] args) {
        String text = "When forty winters shall beseige thy brow,\n" +
                "And dig deep trenches in thy beauty's field,\n" +
                "Thy youth's proud livery, so gazed on now,\n" +
                "Will be a tatter'd weed, of small worth held:\n" +
                "Then being ask'd where all thy beauty lies,\n" +
                "Where all the treasure of thy lusty days,\n" +
                "To say, within thine own deep-sunken eyes,\n" +
                "Were an all-eating shame and thriftless praise.\n" +
                "How much more praise deserved thy beauty's use,\n" +
                "If thou couldst answer 'This fair child of mine\n" +
                "Shall sum my count and make my old excuse,'\n" +
                "Proving his beauty by succession thine!\n" +
                "This were to be new made when thou art old,\n" +
                "And see thy blood warm when thou feel'st it cold.";
        String separateText = splitTextInToWords(text);
    }

    public static String splitTextInToWords(String text) {
        String resultText = "";
        String[] sepWords = text.split("\\s+");
        for (int i = 0; i < sepWords.length; i++) {
            resultText = sepWords[i];
            System.out.println(resultText);
        }
        return resultText;
    }
}