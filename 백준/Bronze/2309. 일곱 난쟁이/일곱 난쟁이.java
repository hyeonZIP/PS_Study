import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static int[] arr = new int[9];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();

        sol(0, 0, new ArrayList<>());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(sb + "");
        bw.flush();
        bw.close();
    }

    private static boolean sol(int sum, int index, ArrayList<Integer> list) {
        if (list.size() == 7) {
            if (sum == 100) {
                for (Integer i : list) {
                    sb.append(i).append("\n");
                }
                return true;
            } else {
                return false;
            }
        }

        if (index >= arr.length) return false;

        if (sol(sum, index + 1, list)) return true;

        list.add(arr[index]);

        if (sol(sum + arr[index], index + 1, list)) return true;

        list.remove(list.size() - 1);

        return false;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        br.close();
    }
}
