import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] inDegree;
    private static int[] buildingTime;
    private static int[] totalBuildingTime;
    private static List<List<Integer>> adj = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();

        sol();

        answer();
    }

    private static void sol() {
        Deque<Integer> q = new ArrayDeque<>();

        for (int buildingNumber = 1; buildingNumber <= N; buildingNumber++) {
            if (inDegree[buildingNumber] == 0) {
                q.offer(buildingNumber);
            }
        }

        while (!q.isEmpty()) {
            int buildingNumber = q.poll();

            for (int nextBuildingNumber : adj.get(buildingNumber)) {
                inDegree[nextBuildingNumber]--;

                totalBuildingTime[nextBuildingNumber] = Math.max(totalBuildingTime[nextBuildingNumber],
                        totalBuildingTime[buildingNumber] + buildingTime[buildingNumber]);

                if (inDegree[nextBuildingNumber] == 0) {
                    q.offer(nextBuildingNumber);
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        inDegree = new int[N + 1];
        buildingTime = new int[N + 1];
        totalBuildingTime = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        StringTokenizer st;

        for (int buildingNumber = 1; buildingNumber <= N; buildingNumber++) {
            st = new StringTokenizer(br.readLine());

            buildingTime[buildingNumber] = Integer.parseInt(st.nextToken());

            while (true) {
                int essentialBuildingNumber = Integer.parseInt(st.nextToken());

                if (essentialBuildingNumber == -1) {
                    break;
                }

                adj.get(essentialBuildingNumber).add(buildingNumber);
                inDegree[buildingNumber]++;
            }
        }
    }

    private static void answer() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            sb.append(totalBuildingTime[i] + buildingTime[i]).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
    }
}