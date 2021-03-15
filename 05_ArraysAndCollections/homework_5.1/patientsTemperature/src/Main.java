public class Main {
    public static void main(String[] args) {
        //read source data
        final int PATIENTS_NUMBER = 30;
        final float MIN_TEMP = 32;
        final float MAX_TEMP = 40;
        final float MIN_HEALTH_TEMP = (float) 36.2;
        final float MAX_HEALTH_TEMP = (float) 36.9;
        int countHealth = 0; //счетчик здоровых
        int countSick = 0; //счетчик больных
        float sumTemp = 0; //суммарная температура

        float[] temp = new float[PATIENTS_NUMBER];

        //processing & display results
        System.out.println("Температуры пациентов: ");
        for (int i = 0; i < PATIENTS_NUMBER; i++) {
            temp[i] = (float) (Math.round((Math.random() * (MAX_TEMP - MIN_TEMP) + MIN_TEMP) * 100)) / 100;
            sumTemp = sumTemp + temp[i];
            if ((temp[i] >= MIN_HEALTH_TEMP) && (temp[i] <= MAX_HEALTH_TEMP)) {
                countHealth++;
            } else {
                countSick++;
            }
            System.out.print(temp[i] + "\t");
        }
        float averageTemp = (float) (Math.round((sumTemp / PATIENTS_NUMBER) * 100)) / 100;
        System.out.println("\nСредняя температура по больнице: " + averageTemp);
        System.out.println("Количество здоровых: " + countHealth);
        System.out.println("Количество больных: " + countSick);
    }
}
