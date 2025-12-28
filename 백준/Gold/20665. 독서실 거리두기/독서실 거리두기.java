import java.io.*;
import java.util.*;

public class Main {
    private static class Seat {
        private int number;
        private int end;

        public Seat(int number, int end) {
            this.number = number;
            this.end = end;
        }
    }

    private static class Reservation {
        private int start;
        private int end;

        public Reservation(String start, String end) {
            int startHour = Integer.parseInt(start.substring(0, 2));
            int startMin = Integer.parseInt(start.substring(2));

            int endHour = Integer.parseInt(end.substring(0, 2));
            int endMin = Integer.parseInt(end.substring(2));

            this.start = startHour * 60 + startMin;
            this.end = endHour * 60 + endMin;
        }
    }

    private static boolean[] seatState;
    private static int answer;
    private static int N, T, P;

    private static PriorityQueue<Reservation> reservationPQ = new PriorityQueue<>(
            Comparator.comparingInt((Reservation o1) -> o1.start).thenComparingInt(o1 -> o1.end));

    private static PriorityQueue<Seat> seatPQ = new PriorityQueue<>(Comparator.comparingInt(o -> o.end));

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        for (int current = 9 * 60; current <= 21 * 60 - 1; current++) {
            while (!seatPQ.isEmpty() && seatPQ.peek().end == current) {
                Seat seat = seatPQ.poll();
                seatState[seat.number] = false;
            }

            while (!reservationPQ.isEmpty() && reservationPQ.peek().start == current) {
                Reservation reservation = reservationPQ.poll();
                if (reservation.start == reservation.end) {
                    continue;
                }
                int number = seatPQ.isEmpty() ? 1 : findFurthestSeat();

                seatState[number] = true;
                seatPQ.offer(new Seat(number, reservation.end));
            }

            if (!seatState[P]) {
                answer++;
            }
        }
    }

    private static int findFurthestSeat() {
        int[] usingSeat = seatPQ.stream().mapToInt(o -> o.number).sorted().toArray();

        int maxDist = -1;
        int targetSeat = -1;

        // 1번좌석을 앉을 때, 가까운 사용중인 좌석 까지의 거리
        maxDist = usingSeat[0] - 1;
        targetSeat = 1;

        // 현재 사용중인 좌석간의 중간 값
        for (int i = 0; i < usingSeat.length - 1; i++) {
            int left = usingSeat[i];
            int right = usingSeat[i + 1];
            int dist = (right - left) / 2;

            if (dist > maxDist) {
                maxDist = dist;
                targetSeat = left + dist;
            }
        }

        // 마지막 좌석을 앉을 때, 가까운 사용중인 좌석까지의 거리
        int lastDist = N - usingSeat[usingSeat.length - 1];

        if (lastDist > maxDist) {
            targetSeat = N;
        }

        return targetSeat;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());// 좌석 수
        T = Integer.parseInt(st.nextToken());// 독서실 예약자 수
        P = Integer.parseInt(st.nextToken());// 선호 좌석 번호

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            String start = st.nextToken();
            String end = st.nextToken();

            reservationPQ.offer(new Reservation(start, end));
        }

        seatState = new boolean[N + 1];
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}