import java.io.*;
import java.util.*;

public class Main {
    private static int[] input = new int[6];
    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        answer += input[5];
        answer += input[4];

        input[0] = Math.max(0, input[0] - input[4] * 11);

        answer += input[3];
        int remaining22 = input[3] * 5;
        if (input[1] >= remaining22) {
            input[1] -= remaining22;
        } else {
            input[0] = Math.max(0, input[0] - (remaining22 - input[1]) * 4);
            input[1] = 0;
        }

        answer += (input[2] + 3) / 4;
        int rem33 = input[2] % 4;
        if (rem33 > 0) {
            if (rem33 == 1) {
                fill(5, 7);
            } else if (rem33 == 2) {
                fill(3, 6);
            } else if (rem33 == 3) {
                fill(1, 5);
            }
        }

        if (input[1] > 0) {
            answer += (input[1] + 8) / 9;
            int rem22 = input[1] % 9;
            if (rem22 > 0) {
                input[0] = Math.max(0, input[0] - (36 - rem22 * 4));
            }
        }

        if (input[0] > 0) {
            answer += (input[0] + 35) / 36;
        }
    }

    private static void fill(int can22, int can11) {
        int use22 = Math.min(can22, input[1]);
        input[1] -= use22;
        input[0] = Math.max(0, input[0] - (can11 + (can22 - use22) * 4));
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 6; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}