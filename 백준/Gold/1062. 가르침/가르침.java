import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static final char[] DEFAULT_WORD = {'a', 'n', 't', 'c', 'i'};
    static ArrayList<Character> word = new ArrayList<>();
    static char[][] toLearnWord;
    static int N, K;
    static int defaultBit;//DEFAULT 비트
    static int newWordBit;//DEFAULT 비트를 제외한 새로운 단어의 비트

    public static void main(String[] args) throws IOException {
        init();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        if (K < 5) {
            bw.write("0");
        } else if (K-5 >= word.size()) {
            bw.write(N+"");
        } else {
            int canLearnWord = K - 5;

            int answer = 0;

            //배울 수 있는 단어만큼 부분집합 생성 후 완전탐색으로 최대값 갱신
            for (int i = 0; i < (1 << word.size()); i++) {
                int combineBit = 0;
                if (Integer.bitCount(i) == canLearnWord) {
                    for (int j = 0; j < word.size(); j++) {
                        if ((i & (1 << j)) != 0) {
                            combineBit |= (1 << word.get(j) - 'a');
                        }
                    }
                }

                //list의 단어 또는 DEFAULT_WORD의 단어에 없는 단어는 카운트 하지 않는다.

                int maxTemp = N;

                for (int j = 0; j < N; j++) {
                    for (char c : toLearnWord[j]) {
                        if ((combineBit & (1 << c - 'a')) == 0 && (defaultBit & (1 << c - 'a')) == 0) {
                            maxTemp--;
                            break;
                        }
                    }
                }

                answer = Math.max(answer, maxTemp);

            }

            bw.write(answer + "");
        }

        bw.close();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        toLearnWord = new char[N][];

        for (int i = 0; i < 5; i++) {
            defaultBit |= 1 << DEFAULT_WORD[i] - 'a';
        }

        for (int i = 0; i < N; i++) {
            char[] temp = br.readLine().toCharArray();
            toLearnWord[i] = temp;

            for (char c : temp) {
                if ((defaultBit & (1 << c - 'a')) == 0 && (newWordBit & (1 << c - 'a')) == 0) {//기본 문자가 아닌 새로운 문자인 경우만 체크
                    word.add(c);
                    newWordBit |= 1 << c - 'a';
                }
            }
        }

//        System.out.println("word = " + word);

//        System.out.println(Integer.bitCount(bit));
    }

}
