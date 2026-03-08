import java.io.*;
import java.util.*;

public class Main {
    static int answer, N, K;
    static int[] eletronics;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    static void sol() {
        List<Integer> powerStrip = new ArrayList<>();

        for (int i = 0; i < eletronics.length; i++) {
            int currentDevice = eletronics[i];

            if (powerStrip.contains(currentDevice)) {
                continue;
            }

            if (powerStrip.size() < N) {
                powerStrip.add(currentDevice);
                continue;
            }

            if (powerStrip.size() >= N) {
                int targetIdx = -1;
                int farthestNextUse = -1;

                for (int j = 0; j < powerStrip.size(); j++) {
                    int device = powerStrip.get(j);
                    int nextUse = Integer.MAX_VALUE;

                    for (int k = i + 1; k < K; k++) {
                        if (eletronics[k] == device) {
                            nextUse = k;
                            break;
                        }
                    }

                    if (nextUse > farthestNextUse) {
                        farthestNextUse = nextUse;
                        targetIdx = j;
                    }
                }

                powerStrip.remove(targetIdx);
                powerStrip.add(currentDevice);
                answer++;
            }
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());// 멀티탭 구멍 개수
        K = Integer.parseInt(st.nextToken());// 전자제품 사용 횟수

        // 전자제품 사용 순서
        eletronics = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}