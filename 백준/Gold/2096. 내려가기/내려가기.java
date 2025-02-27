import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dpMax = new int[N + 1][3];
        int[][] dpMin = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            dpMax[i][0] = Math.max(dpMax[i - 1][0], dpMax[i - 1][1]) + arr[i][0];
            dpMax[i][1] = Math.max(dpMax[i - 1][2], Math.max(dpMax[i - 1][0], dpMax[i - 1][1])) + arr[i][1];
            dpMax[i][2] = Math.max(dpMax[i - 1][1], dpMax[i - 1][2]) + arr[i][2];

            dpMin[i][0] = Math.min(dpMin[i - 1][0], dpMin[i - 1][1]) + arr[i][0];
            dpMin[i][1] = Math.min(dpMin[i - 1][2], Math.min(dpMin[i - 1][0], dpMin[i - 1][1])) + arr[i][1];
            dpMin[i][2] = Math.min(dpMin[i - 1][1], dpMin[i - 1][2]) + arr[i][2];
        }

        int max = Math.max(Math.max(dpMax[N][0], dpMax[N][1]), dpMax[N][2]);
        int min = Math.min(Math.min(dpMin[N][0], dpMin[N][1]), dpMin[N][2]);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(max + " " + min);
        bw.flush();
        bw.close();
        br.close();
    }
}
