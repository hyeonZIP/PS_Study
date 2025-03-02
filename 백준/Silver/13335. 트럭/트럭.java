import java.util.*;
import java.io.*;

/**
 * n개의 트럭
 * 트럭의 순서는 바꿀 수 없다
 * 트럭의 무게는 서로 같지 않을 수 있다
 * w대의 트럭만 다리위에 동시에 올라간다
 * 다리의 길이 w 단위길이
 * 다리의 최대 하중 L보다 작거나 같은 트럭들의 무게
 * 다리위에 완전히 올라가지 않은 트럭의 무게는 포함 x
 */
public class Main {


    static int n, w, L, answer;
    static ArrayDeque<Integer> truck = new ArrayDeque<>();
    static ArrayDeque<Integer> bridge = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        init();

        bridgePassingProcess();

        System.out.println(answer);
    }

    private static void bridgePassingProcess() {
        int currentBridgeWeight = 0;

        for (int i=0; i<w; i++){
            bridge.offer(0);
        }

        while(!bridge.isEmpty()){
            answer++;

            currentBridgeWeight -= bridge.poll();

            if(!truck.isEmpty()) {
                if (truck.peek() + currentBridgeWeight <= L) {
                    currentBridgeWeight += truck.peek();
                    bridge.offer(truck.poll());
                } else bridge.offer(0);
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            truck.offer(Integer.parseInt(st.nextToken()));
        }
    }
}
