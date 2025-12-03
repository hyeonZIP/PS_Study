import java.io.*;
import java.util.*;

public class Main {
    private static Map<Integer, List<Long>> map = new HashMap<>();
    private static long target;
    private static int N;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();

        sol();

        answer();
    }

    private static void sol() {
        List<Integer> startLines = findStartLines();

        if (startLines.isEmpty()) {
            return;
        }

        for (int line : startLines) {
            dfs(0, new boolean[11], line, 0);
        }
    }

    private static void dfs(int depth, boolean[] visited, int line, int count) {
        visited[line] = true;

        if (isTargetHere(line)) {
            answer = Math.min(answer, count);
            return;
        }

        if (depth == N) {
            return;
        }

        Set<Integer> nextLines = findNextLines(line, visited);

        for (int nextLine : nextLines) {
            dfs(depth + 1, visited, nextLine, count + 1);
        }
    }

    private static Set<Integer> findNextLines(int line, boolean[] visited) {
        Set<Integer> nextLines = new HashSet<>();

        for (long station : map.get(line)) {
            for (Map.Entry<Integer, List<Long>> entry : map.entrySet()) {
                if (entry.getValue().contains(station) && !visited[entry.getKey()]) {
                    nextLines.add(entry.getKey());
                }
            }
        }

        return nextLines;
    }

    private static boolean isTargetHere(int station) {
        return map.get(station).contains(target);
    }

    private static List<Integer> findStartLines() {
        List<Integer> startLines = new ArrayList<>();

        for (Map.Entry<Integer, List<Long>> entry : map.entrySet()) {
            if (entry.getValue().contains(Long.valueOf(0))) {
                startLines.add(entry.getKey());
            }
        }

        return startLines;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int stationCount = Integer.parseInt(st.nextToken());

            for (int j = 0; j < stationCount; j++) {
                long station = Long.parseLong(st.nextToken());

                List<Long> stationList = map.getOrDefault(i, new ArrayList<>());
                stationList.add(station);

                map.put(i, stationList);
            }
        }

        target = Long.parseLong(br.readLine());
    }

    private static void answer() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        bw.write(String.valueOf(answer));
        bw.close();
    }
}