import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        StringTokenizer st;

        while (testcase-- > 0) {
            st = new StringTokenizer(br.readLine());

            int memoryAmount = Integer.parseInt(st.nextToken()); // 1000 5 1
            int codeLength = Integer.parseInt(st.nextToken());
            int inputLength = Integer.parseInt(st.nextToken());

            int[] memory = new int[memoryAmount];

            String[] cmd = br.readLine().split("");// +-.,
            String[] input = br.readLine().split("");// a

            int pointerIdx = 0;
            int timeOutCheck = 0;

            int maxIdx = -1;
            int inputIdx = 0;

            int[] pair = new int[codeLength];
            Deque<Integer> loopStack = new ArrayDeque<>();

            for (int i = 0; i < codeLength; i++) {
                if (cmd[i].equals("[")) {
                    loopStack.push(i);
                } else if (cmd[i].equals("]")) {
                    int left = loopStack.pop();
                    pair[left] = i;
                    pair[i] = left;
                }
            }

            for (int cmdIdx = 0; cmdIdx < cmd.length && timeOutCheck <= 100_000_000; cmdIdx++) {
                String command = cmd[cmdIdx];
                timeOutCheck++;

                switch (command) {
                    case "+":
                        memory[pointerIdx] = (memory[pointerIdx] + 1) % 256;
                        break;
                    case "-":
                        memory[pointerIdx] = memory[pointerIdx] - 1 < 0 ? 255 : memory[pointerIdx] - 1;
                        break;
                    case "<":
                        pointerIdx = (pointerIdx - 1 + memoryAmount) % memoryAmount;
                        break;
                    case ">":
                        pointerIdx = (pointerIdx + 1) % memoryAmount;
                        break;
                    case "[":
                        if (memory[pointerIdx] == 0) {
                            cmdIdx = pair[cmdIdx];
                        }
                        break;
                    case "]":
                        if (memory[pointerIdx] != 0) {
                            if (timeOutCheck > 50_000_000) {
                                maxIdx = Math.max(maxIdx, cmdIdx);
                            }
                            cmdIdx = pair[cmdIdx];
                        }
                        break;
                    case ".":
                        // 포인터가 가리키는 수 출력
                        break;
                    case ",":
                        if (inputIdx < inputLength) {
                            memory[pointerIdx] = input[inputIdx].charAt(0);
                            inputIdx++;
                        } else {
                            memory[pointerIdx] = 255;
                        }
                        break;
                }
            }

            if (timeOutCheck <= 50_000_000) {
                answer.append("Terminates");
            } else {
                answer.append("Loops")
                        .append(" ")
                        .append(pair[maxIdx])
                        .append(" ")
                        .append(maxIdx);
            }
            answer.append("\n");
        }
        print();
    }

    static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}