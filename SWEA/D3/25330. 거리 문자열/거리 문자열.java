import java.io.*;
import java.util.*;

public class Solution {
    private static int testCount;
    private static String[] testCases;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();

        sol();

        print();
    }

    private static void sol() {
        for (String testCase : testCases) {
            int[] existed = new int[10];
            boolean yesFlag = true;

            for (int i = 0; i < testCase.length(); i++) {
                int middleNumberCount = Character.getNumericValue(testCase.charAt(i));

                if (existed[middleNumberCount] > 2) {
                    sb.append("no").append("\n");
                    yesFlag = false;
                    break;
                }

                if (existed[middleNumberCount] == 1) {
                    existed[middleNumberCount]++;
                    continue;
                }

                if (i + middleNumberCount + 1 < testCase.length()
                        && testCase.charAt(i) == testCase.charAt(i + middleNumberCount + 1)) {
                    existed[middleNumberCount]++;
                    continue;
                } else {
                    sb.append("no").append("\n");
                    yesFlag = false;
                    break;
                }
            }

            if (yesFlag) {
                sb.append("yes").append("\n");
            }
        }
    }

    private static void init() throws IOException {
        Scanner sc = new Scanner(System.in);

        testCount = Integer.parseInt(sc.nextLine());

        testCases = new String[testCount];

        for (int i = 0; i < testCount; i++) {
            testCases[i] = sc.nextLine();
        }

        sc.close();
    }

    private static void print() {
        System.out.println(sb.toString());
    }
}
