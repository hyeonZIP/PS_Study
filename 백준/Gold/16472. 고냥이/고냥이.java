import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int[] arr = new int[26];
    private static String str;
    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    public static void sol() {
        List<Character> list = new ArrayList<>();
        int start = 0;
        int end = 0;

        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);

            arr[current - 'a'] = i;

            if (list.contains(current)) {

            } else {
                list.add(current);
            }

            if (list.size() < N) {
                end = i;
                continue;
            }

            if (list.size() == N) {
                end = i;
                answer = Math.max(answer, end - start + 1);
                continue;
            }

            if (list.size() > N) {
                start = findStartIndex(list) + 1;
                char startChar = findStartCharacter(list);
                end = i;

                list.remove(list.indexOf(startChar));

                answer = Math.max(answer, end - start + 1);
                continue;
            }
        }

        answer = Math.max(answer, end - start + 1);
    }

    private static int findStartIndex(List<Character> list) {
        int start = Integer.MAX_VALUE;

        for (Character c : list) {
            start = Math.min(arr[c - 'a'], start);
        }

        return start;
    }

    private static char findStartCharacter(List<Character> list) {
        char start = ' ';
        int startIndex = Integer.MAX_VALUE;

        for (Character c : list) {
            if (startIndex >= arr[c - 'a']) {
                startIndex = arr[c - 'a'];
                start = c;
            }
        }

        return start;
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        str = br.readLine();
    }

    public static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}