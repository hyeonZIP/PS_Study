import java.io.*;
import java.util.*;

public class Main {
    private static class Lecture {
        int dDay;
        int money;

        public Lecture(int dDay, int money) {
            this.dDay = dDay;
            this.money = money;
        }
    }

    private static int answer;
    private static int n;
    private static int[] arr;
    private static PriorityQueue<Lecture> pq = new PriorityQueue<>(Comparator.comparingInt(o -> -o.money));

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        int count = 0;
        int totalMoney = 0;

        while (!pq.isEmpty() && count <= n) {
            Lecture lecture = pq.poll();

            if (hasValidDay(lecture)) {
                reserveLecture(lecture);
                count++;
                totalMoney += lecture.money;
                continue;
            }
        }

        answer = totalMoney;
    }

    private static void reserveLecture(Lecture lecture) {
        for (int i = lecture.dDay; i >= 1; i--) {
            if (arr[i] == 0) {
                arr[i] = lecture.money;
                return;
            }
        }
    }

    private static boolean hasValidDay(Lecture lecture) {
        for (int i = lecture.dDay; i >= 1; i--) {
            if (arr[i] == 0) {
                return true;
            }
        }
        return false;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[10001];

        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());// 강연료
            int d = Integer.parseInt(st.nextToken());// 일자
            pq.offer(new Lecture(d, p));
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}