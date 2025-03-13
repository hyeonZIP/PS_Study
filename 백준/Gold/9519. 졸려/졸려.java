import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * 단어의 뒷 부분 절반이 앞 부분과 섞인다
 * 홀수인 경우 뒷 부분의 길이가 짧다
 * 마지막 글자가 첫 번째 글자와 두 번째 글자 사이로 이동
 * 뒤에서 두 번째 글자가 두 번째 글자와 세 번째 글자 사이로 이동
 * 뒤에서 k번째 글자는 앞에서부터 k번째와 k+1번째 글자 사이로 이동
 * <p>
 * 1 2 3 4 5 6
 * ㄲㅂ
 * 1 6 2 5 3 4
 * ㄲㅂ
 * 1 4 6 3 2 5
 * ###################################해결 방법#############################
 * 1. 첫 번째 글자는 바뀌지 않음
 * 2. 투 포인터 형식으로 하나는 왼쪽 하나는 오른쪽 부터 문자열을 채워넣는다
 * 3. 처음에는 투 포인터 형식으로 넣다가 아직 반복 횟수는 남았지만 정답과 같은 문자열이 생길 경우 그때는 수식으로 반복 횟수 차감
 * <p>
 * a b c d e f(원본)
 * 깜빡
 * a f b e c d
 * 깜빡
 * a d f c b e
 * ㄲㅂ
 * a e d b f c
 * ㄲㅂ
 * a c e f d b
 * ㄲㅂ
 * a b c d e f(원본) 5번 깜빡에 원본
 * <p>
 * <p>
 * X번 깜빡임
 * <p>
 * 원래 단어 출력
 * <p>
 * X 최대 10억
 * 문자 길이 최대 31,000
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long X = Long.parseLong(br.readLine());

        char[] originalString = br.readLine().toCharArray();
        int len = originalString.length;
        char[] currentString = originalString;
        if (len < 3) {//문자열 길이가 2 이하면 바뀌지 않음
            bw.write(new String(originalString));
        } else {
            long count = 0;
            for (long blinking = 0; blinking < X; blinking++) {

                int fromStart = 1;
                int toStart = 1;
                int fromEnd = 2;
                int toEnd = 1;
                char[] previousString = new char[len];
                previousString[0] = currentString[0];
                while (true) {

                    previousString[len - toStart] = currentString[fromStart];
                    previousString[toEnd] = currentString[fromEnd];
//                    System.out.println("previousString 2개 이동 : " + Arrays.toString(previousString));
                    fromStart += 2;
                    fromEnd += 2;
                    toStart++;
                    toEnd++;

                    if (fromEnd >= len) {
                        if (fromStart >= len) {
                            break;
                        } else {
                            previousString[toStart] = currentString[fromStart];
//                            System.out.println("previousString 마지막 하나 이동 : " + Arrays.toString(previousString));
                            break;
                        }
                    }
                }
                count++;
//                System.out.println("@@previousString 깜빡임 완료 : " + Arrays.toString(previousString));
//                System.out.println("$$currentString에 덮어씌우기 : " + Arrays.toString(currentString));
                currentString = previousString;

                if (Arrays.equals(currentString, originalString)) {
//                    System.out.println(count+"번 만에 같아짐");
                    //만약 count 번 만에 오리지널 문자열로 돌아왔을 경우
                    blinking = X-1 - X % count;
                }
            }
            bw.write(new String(currentString));
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
