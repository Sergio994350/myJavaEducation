package array_max_value;

public class ArrayMaxValue
{
    public static int getMaxValue(int[] values)
    {
        int maxValue = Integer.MIN_VALUE;
        if (values != null && values.length > 0) {
            for (int value : values) {
                if (value > maxValue) {
                    maxValue = value;
                }
            }
        }
        return maxValue;
    }
}