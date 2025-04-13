import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static ArrayDeque<Integer> xq = new ArrayDeque<>();
    static ArrayDeque<Integer> yq = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        init();

        sol();
    }

    private static void sol() {
        long xSum = 0L;
        long ySum = 0L;


        int previousXPos = xq.poll();
        int previousYPos = yq.poll();

        while (!xq.isEmpty() || !yq.isEmpty()) {
            int nextXPos = xq.poll();
            int nextYPos = yq.poll();

            xSum += (long) previousXPos * nextYPos;
            ySum += (long) previousYPos * nextXPos;

            previousXPos = nextXPos;
            previousYPos = nextYPos;
        }

        double answer = Math.abs(xSum - ySum) / 2.0;

        System.out.printf("%.1f", answer);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            xq.offer(Integer.parseInt(st.nextToken()));
            yq.offer(Integer.parseInt(st.nextToken()));
        }

        xq.offer(xq.peek());
        yq.offer(yq.peek());
    }
}
