import java.io.*;
import java.sql.Array;
import java.util.*;

/**
 * 7가지 메뉴
 * 1. A B: A주식 B개 구입, 살수 없다면 한주도 사지 않는다
 * 2. A B: A주식 B개 판매, 보유 주식이 없다면 팔지 않는다. 3개 가지고 있는데 10개 팔라고 하면 3개를 판다
 * 3. A C: A주가가 C원 오름, -4일 경우 주가가 4원 떨어짐
 * 4. D C: D그룹에 속하는 회사 주가 C원 오름 음수일 경우 떨어짐
 * 5. D E: D그룹에 속하는 회사 주가 E% 오름 음수일 경우 떨어짐
 * 6. 현재 하이비가 보유하고 있는 현금 출력
 * 7. 주식을 전부 팔았을 때 보유하고 있는 혐금 출력
 * <p>
 * 특이사항
 * 대소문자 구분
 * 주식 사고 팔 때 단위 10원 일의 자리는 버린다
 * 주식을 전부 팔았을 때 2 곱하기 10의 12승을 초과하지 않는다.
 * 각 메뉴에 맞게 동작하도록 구현한다
 * 사용자 주식 보유 현황 HashMap과 시장 주식 HashMap 사용자 보유 자금 class
 * 회사 이름은 중복 x
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, Q;
    static long M;

    static HashMap<Integer, List<String>> stockByGroup = new HashMap<>();//그룹핑 된 주식
    static HashMap<String, Long> stockByName = new HashMap<>();//주식이름과 주당 가격
    static HashMap<String, Integer> myStock = new HashMap<>();//보유 주식 이름과 개수

    public static void main(String[] args) throws IOException {
        init();

        command();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());//상장 회사 수
        M = Long.parseLong(st.nextToken());//보유현금
        Q = Integer.parseInt(st.nextToken());//입력할 메뉴의 갯수

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int G = Integer.parseInt(st.nextToken());//그룹 번호
            String H = st.nextToken();//회사 이름
            long P = Integer.parseInt(st.nextToken());//주당 가격

            List<String> stockList = stockByGroup.getOrDefault(G, new ArrayList<>());//기존에 생성된 그룹을 가져오거나 새로 만듦
            stockList.add(H);

            stockByGroup.put(G, stockList);
            stockByName.put(H, P);
        }
    }

    private static void command() throws IOException {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case 1:
                    buyCommand();
                    break;
                case 2:
                    sellCommand();
                    break;
                case 3:
                    changePriceCommand();
                    break;
                case 4:
                    changeGroupPriceCommand();
                    break;
                case 5:
                    changeGroupPricePercentageCommand();
                    break;
                case 6:
                    printCurrentMyMoney(sb);
                    break;
                case 7:
                    printWholeMyMoney(sb);
            }
        }
        System.out.print(sb);
    }

    private static void printWholeMyMoney(StringBuilder sb) {
        long wholeMoney = 0;
        for (Map.Entry<?, ?> entry : myStock.entrySet()) {
            String stockName = (String) entry.getKey();
            int stockAmount = (int) entry.getValue();

            wholeMoney += stockByName.getOrDefault(stockName, 0L) * stockAmount;
        }
        wholeMoney += M;
        sb.append(wholeMoney).append("\n");
    }

    private static void printCurrentMyMoney(StringBuilder sb) {
        sb.append(M).append("\n");
    }

    private static void changeGroupPricePercentageCommand() {
        int groupNumber = Integer.parseInt(st.nextToken());//변경 그룹 번호
        int changedPercentage = Integer.parseInt(st.nextToken());//변동 퍼센테이지

        List<String> stockList = stockByGroup.get(groupNumber);

        for (String stockName : stockList) {
            long currentPrice = stockByName.getOrDefault(stockName, 0L);

            long changedPrice = currentPrice * (100+changedPercentage) / 100;
            changedPrice -= changedPrice % 10;//일의 자리 삭제

            changedPrice = Math.max(0,changedPrice);

            stockByName.put(stockName, changedPrice);
        }
    }

    private static void changeGroupPriceCommand() {
        int groupNumber = Integer.parseInt(st.nextToken());//변경 그룹 번호
        int changedAmount = Integer.parseInt(st.nextToken());//변동 금액

        List<String> stockList = stockByGroup.get(groupNumber);

        for (String stockName : stockList) {
            long currentPrice = stockByName.getOrDefault(stockName, 0L);

            long changedPrice = currentPrice + changedAmount;

            stockByName.put(stockName, changedPrice);
        }
    }

    private static void changePriceCommand() {
        String stockName = st.nextToken();//변경 주식 이름
        int changedAmount = Integer.parseInt(st.nextToken());//변동 금액

        long currentPrice = stockByName.getOrDefault(stockName, 0L);
        long changedPrice = currentPrice + changedAmount;

        stockByName.put(stockName, Math.max(0, changedPrice));
    }

    private static void sellCommand() {
        String stockName = st.nextToken();//판매 주식 이름
        int stockCount = Integer.parseInt(st.nextToken());//판매 개수

        long currentPrice = stockByName.getOrDefault(stockName, 0L);
        int holdAmount = myStock.getOrDefault(stockName, 0);
        int soldAmount = Math.min(stockCount, holdAmount);//보유 수량과 원하는 판매 수량 중 적은 값이 판매 가능한 개수이다

        M += soldAmount * currentPrice;
        myStock.put(stockName, holdAmount - soldAmount);//해당 결과로 0이 들어갈 수도 있음
    }

    private static void buyCommand() {
        String stockName = st.nextToken();//구매 주식 이름
        int stockCount = Integer.parseInt(st.nextToken());//구매 개수

        long paidAmount = stockByName.getOrDefault(stockName, 0L) * stockCount;

        if (M >= paidAmount) {
            M -= paidAmount;
            myStock.put(stockName, myStock.getOrDefault(stockName, 0) + stockCount);//보유 주식 업데이트
        }
    }
}


