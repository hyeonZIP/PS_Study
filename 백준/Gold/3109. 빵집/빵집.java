import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static final char BUILDING = 'x';
    static final char EMPTY = '.';

    static int R, C;
    static char[][] map;
    static boolean[][] visited;

    static int[] dy = {1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int y = 0; y < R; y++) {
            map[y] = br.readLine().toCharArray();
        }

        int answer = 0;
        for (int y = 0; y < R; y++) {
            answer += dfs(y);
        }

        System.out.println(answer);
    }

    private static int dfs(int y) {
        ArrayDeque<int[]> stack = new ArrayDeque<>();

        stack.push(new int[]{y, 0});

        int minPipeY = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            int[] arr = stack.pop();
            visited[arr[0]][arr[1]] = true;

            for (int i = 0; i < 3; i++) {
                int py = arr[0] + dy[i];
                int px = arr[1] + 1;

                if (isOutOfRange(py, px) || map[py][px] == BUILDING || visited[py][px]) continue;

                if (px == C-1) {
                    minPipeY = Math.min(minPipeY,py);
                    continue;
                }

                stack.push(new int[]{py,px});
            }

            if (minPipeY != Integer.MAX_VALUE){
                visited[minPipeY][C-1] = true;
                return 1;
            }
        }

        return 0;
    }

    private static boolean isOutOfRange(int y, int x) {
        return x < 0 || y < 0 || x >= C || y >= R;
    }
}
