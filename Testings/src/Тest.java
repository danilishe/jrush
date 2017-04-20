import java.util.Arrays;

/**
 * Created by Данил on 26.02.2017.
 */
public class Тest {
    public static void main (String[] args) {

        int[] array1 = {3, 83, 17, 99, 26, 16, 55, 100, 22, 13};
        int[] array2 = array1.clone();
        Arrays.sort(array2);

        int[] result = new int[array1.length];
        for (int j = 0; j < array1.length; j++) {
            for (int i = 0; i < array2.length; i++) {
                if (array1[j] == array2[i]) {
                    result[j] = (int) ((double) i  / array1.length * 5) + 1;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(result));
    }
}