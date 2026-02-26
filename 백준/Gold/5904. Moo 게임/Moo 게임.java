import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String answer;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    static void sol() {
        String s0 = "moo";

        int mooLength = s0.length();
        int k = 0;

        while (mooLength <= N) {
            k++;
            mooLength = mooLength * 2 + getMiddleMooLength(k);
        }

        answer = dfs(k, mooLength);
    }

    static int getMiddleMooLength(int k) {
        return "m".length() + "o".length() * (k + 2);
    }

    static String dfs(int k, int mooLength) {
        if (k == 0) {
            if (N == 1) {
                return "m";
            } else {
                return "o";
            }
        }

        int previousMooLength = (mooLength - getMiddleMooLength(k)) / 2;

        if (isFrontMoo(previousMooLength)) {
            return dfs(k - 1, previousMooLength);
        }

        if (isMiddleMoo(k, previousMooLength)) {
            if (previousMooLength + 1 == N) {
                return "m";
            } else {
                return "o";
            }
        }
        if (isBackMoo(k, previousMooLength)) {
            N -= (previousMooLength + getMiddleMooLength(k));
            return dfs(k - 1, previousMooLength);
        }

        return "ERROR";
    }

    static boolean isMiddleMoo(int k, int previousMooLength) {
        return previousMooLength + 1 <= N && previousMooLength + getMiddleMooLength(k) > N;
    }

    static boolean isFrontMoo(int previousMooLength) {
        return N <= previousMooLength;
    }

    static boolean isBackMoo(int k, int previousMooLength) {
        return previousMooLength + getMiddleMooLength(k) <= N;
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
    }

    static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(answer);
        bw.close();
    }
}
