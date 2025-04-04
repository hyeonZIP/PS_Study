import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int testcase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testcase; t++) {
            int n = Integer.parseInt(br.readLine());
            int max = Integer.MIN_VALUE;
            ArrayList<Integer> result = new ArrayList<>();

            for (int i = 1; i < (1 << n); i++) {
                ArrayList<Integer> list = new ArrayList<>();

                for (int j = 0; j < n; j++) {
                    if (((1 << j) & i) != 0) {//j번째만 1인 비트와 i가 동일할 경우 수열에 추가
                        list.add(j + 1);
                    }
                }

                if (isFlag(list)) {//완탐
                    if (list.size() > max) {//최대값과 수열 업데이트
                        max = list.size();
                        result.clear();
                        result.addAll(list);
                    }
                }
            }
            sb.append(result.size()).append("\n");
            for (Integer a : result) {
                sb.append(a).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean isFlag(ArrayList<Integer> list) {

        for (int j = 0; j < list.size() - 2; j++) {
            for (int k = j + 1; k < list.size() - 1; k++) {
                for (int l = k + 1; l < list.size(); l++) {
                    if ((list.get(j) ^ list.get(k) ^ list.get(l)) == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
