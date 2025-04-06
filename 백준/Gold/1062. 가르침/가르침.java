import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static final char[] DEFAULT_WORD = {'a', 'n', 't', 'c', 'i'};
    static int[] words;
    static int N, K, answer;
    static int visited;

    public static void main(String[] args) throws IOException {
        init();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        if (K < 5) {
            bw.write("0");
        } else {

            dfs(0, 0);

            bw.write(answer + "");
        }

        bw.close();
    }

    private static void dfs(int depth, int index) {
        if (depth == K - 5) {
            int temp = 0;

            for (int word : words) {
                int check = visited | word;
                if (check == visited) temp++;
            }

            answer = Math.max(answer, temp);
            return;
        }

        for (int i = index; i < 26; i++) {
            if ((visited & (1 << i)) > 0) continue;
            visited = visited | 1 << i;
            dfs(depth + 1, i + 1);
            visited = visited ^ 1 << i;
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new int[N];

        for (int i = 0; i < 5; i++) {
            visited |= 1 << DEFAULT_WORD[i] - 'a';
        }

        for (int i = 0; i < N; i++) {
            String temp = br.readLine();

            char[] newWords = temp.substring(4, temp.length() - 4).toCharArray();

            for (char newWord : newWords) {
                words[i] |= 1 << newWord - 'a';
            }
        }
    }

}
