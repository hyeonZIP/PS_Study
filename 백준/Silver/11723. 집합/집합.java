import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;

        int commandCount = Integer.parseInt(br.readLine());

        int bit = 0;

        for (int i = 0; i < commandCount; i++) {

            st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();

            if (cmd.equals("all")) {
                bit = (1 << 20) - 1;
                continue;
            } else if (cmd.equals("empty")) {
                bit = 0;
                continue;
            }

            int num = Integer.parseInt(st.nextToken());
            num -= 1;
            switch (cmd) {
                case "add":
                    bit |= 1 << num;
                    break;
                case "remove":
                    bit &= ~(1 << num);
                    break;
                case "check":
                    if ((bit & (1 << num)) != 0) {
                        sb.append("1").append("\n");
                    } else {
                        sb.append("0").append("\n");
                    }
                    break;
                case "toggle":
                    bit ^= 1 << num;

            }
        }

        bw.write(sb + "");
        bw.close();
        br.close();
    }
}
