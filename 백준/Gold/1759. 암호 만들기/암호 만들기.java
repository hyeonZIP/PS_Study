import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int L, C;
    static char[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        dfs(0, 0, new char[L]);

        System.out.print(sb);
    }

    private static void dfs(int depth, int index, char[] code) {

        if (depth == L) {
            if (check(code)) {
                for (char c : code) {
                    sb.append(c);
                }
                sb.append("\n");
            }
            return;
        }

        for (int i = index; i < C; i++) {
            code[depth] = arr[i];
            dfs(depth + 1, i + 1, code);
        }

    }

    private static boolean check(char[] code) {

        int moUmm = 0;
        int zaUmm = 0;

        for (char c : code) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') moUmm++;
            else zaUmm++;
        }

        return moUmm >= 1 && zaUmm >= 2;
    }
}
