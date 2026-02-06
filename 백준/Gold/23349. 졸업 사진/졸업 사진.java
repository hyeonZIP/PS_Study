import java.io.*;
import java.util.*;

public class Main {
    private static class Congestion {
        private String placeName;
        private int startTime, endTime, maxCongestion;

        public Congestion(String placeName, int startTime, int endTime, int maxCongestion) {
            this.placeName = placeName;
            this.startTime = startTime;
            this.endTime = endTime;
            this.maxCongestion = maxCongestion;
        }
    }

    private static StringBuilder answer = new StringBuilder();
    private static int N;
    private static Map<String, int[]> placeCongestion = new HashMap<>();

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        PriorityQueue<Congestion> pq = new PriorityQueue<>(Comparator
                .comparingInt((Congestion o) -> -o.maxCongestion)
                .thenComparing(o -> o.placeName));

        for (Map.Entry<String, int[]> entry : placeCongestion.entrySet()) {
            int[] congestionTime = getCongestionTime(entry.getKey());
            pq.offer(new Congestion(entry.getKey(), congestionTime[0], congestionTime[1], congestionTime[2]));
        }

        Congestion congestion = pq.poll();

        answer.append(congestion.placeName)
                .append(" ")
                .append(congestion.startTime)
                .append(" ")
                .append(congestion.endTime + 1);
    }

    private static int[] getCongestionTime(String placeName) {
        int[] congestionTime = new int[3];

        int[] timeTable = placeCongestion.get(placeName);

        int maxCongestion = Integer.MIN_VALUE;
        int maxCongestionStartIndex = 0;
        int maxCongestionEndIndex = 0;
        boolean isNeedCongestionEndTimeChecking = false;

        for (int i = 1; i <= 50000; i++) {
            if (maxCongestion < timeTable[i]) {
                maxCongestion = timeTable[i];
                maxCongestionStartIndex = i;
                isNeedCongestionEndTimeChecking = true;
            }

            if (isNeedCongestionEndTimeChecking && timeTable[i] != maxCongestion) {
                maxCongestionEndIndex = i - 1;
                isNeedCongestionEndTimeChecking = false;
            }
        }

        congestionTime[0] = maxCongestionStartIndex;
        congestionTime[1] = maxCongestionEndIndex;
        congestionTime[2] = maxCongestion;

        return congestionTime;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        Set<String> student = new HashSet<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String studentName = st.nextToken();
            String placeName = st.nextToken();
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken()) - 1;

            if (student.contains(studentName)) {
                continue;
            }

            student.add(studentName);

            int[] congestion = placeCongestion.getOrDefault(placeName, new int[50001]);

            for (int j = startTime; j <= endTime; j++) {
                congestion[j]++;
            }

            placeCongestion.put(placeName, congestion);
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}