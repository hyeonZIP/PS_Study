import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 1 달에 4일 이상 이면 1 달 이용권이 이득
 * 3 달에 10일 이상 이용하면 3달 이용권이 이득
 * 1년에 3달 이상 or 30일 이상 이용 시 1년 이용권이 이득
 */
public class Solution {
    

    static int[] calender;
    static int oneDayPass,oneMonthPass,threeMonthPass,oneYearPass;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testcase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testcase; t++) {
            calender = new int[12];
            int[] dp = new int[12];


            StringTokenizer st = new StringTokenizer(br.readLine());
            oneDayPass = Integer.parseInt(st.nextToken());
            oneMonthPass = Integer.parseInt(st.nextToken());
            threeMonthPass = Integer.parseInt(st.nextToken());
            oneYearPass = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i=0; i<12; i++){
                int plan = Integer.parseInt(st.nextToken());
                calender[i] = plan;
            }

            dp[0] = Math.min(calender[0]*oneDayPass, oneMonthPass);
//            System.out.println("dp[0] = " + dp[0]);
            dp[1] = Math.min(calender[1]*oneDayPass, oneMonthPass)+dp[0];
//            System.out.println("dp[1] = " + dp[1]);
            dp[2] = Math.min(Math.min(calender[2]*oneDayPass, oneMonthPass)+dp[1],threeMonthPass);
//            System.out.println("dp[2] = " + dp[2]);
            dp[3] = Math.min(Math.min(dp[0]+threeMonthPass,dp[2]+calender[3]*oneDayPass),dp[2]+oneMonthPass);
//            System.out.println("dp[3] = " + dp[3]);
            for (int i=4; i<12; i++){
                dp[i] = Math.min(Math.min(dp[i-3]+threeMonthPass,dp[i-1]+calender[i]*oneDayPass),dp[i-1]+oneMonthPass);
//                System.out.println("dp["+i+"] = " + dp[i]);
            }

            sb.append("#").append(t+1).append(" ").append(Math.min(dp[11],oneYearPass)).append("\n");
        }//testcase
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
