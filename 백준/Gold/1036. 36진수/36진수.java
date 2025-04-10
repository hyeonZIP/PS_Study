import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static final String s = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final char[] dict = s.toCharArray();
    static int N, K;
    static HashMap<Character, BigInteger> changedMap = new HashMap<>();
    static HashMap<Character, BigInteger> originalMap = new HashMap<>();
    static HashMap<Character, BigInteger> conversionMap = new HashMap<>();
    static HashMap<Character, Integer> dictMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        init();

        ArrayList<Character> changeList = changeNumber();

        BigInteger sum = sumNumber(changeList);

        ArrayDeque<Character> stack = conversion(sum);

        while(!stack.isEmpty()){
            System.out.print(stack.pop());
        }

    }

    private static ArrayDeque<Character> conversion(BigInteger sum) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        while (true) {
            BigInteger[] divAndMod = sum.divideAndRemainder(BigInteger.valueOf(36));
            sum = divAndMod[0];
//            System.out.println("divAndMod[0] = " + divAndMod[0]);
//            System.out.println("divAndMod[1] = " + divAndMod[1]);
            if (divAndMod[0] == BigInteger.ZERO) {
                stack.push(dict[(divAndMod[1].intValue())]);
                break;
            }
//            System.out.println("divAndMod[1].intValue() = " + divAndMod[1].intValue());
//            System.out.println("dict[(divAndMod[1].intValue())] = " + dict[(divAndMod[1].intValue())]);
            stack.push(dict[(divAndMod[1].intValue())]);
        }
        return stack;
    }

    private static BigInteger sumNumber(ArrayList<Character> changeList) {
        ArrayList<Character> originalKeySet = new ArrayList<>(originalMap.keySet());

        BigInteger sum = BigInteger.ZERO;

        for (Character c : originalKeySet) {
            if (changeList.contains(c)) {
                sum = sum.add(conversionMap.get(c));
                continue;
            }
            sum = sum.add(originalMap.get(c));
        }

//        System.out.println("sum = " + sum);

        return sum;
    }

    private static ArrayList<Character> changeNumber() {
        ArrayList<Character> keySet = new ArrayList<>(changedMap.keySet());

        keySet.sort((o1, o2) -> (-1) * changedMap.get(o1).compareTo(changedMap.get(o2)));

        ArrayList<Character> changeList = new ArrayList<>();
        int index = 0;
        while (changeList.size() != K && index < changedMap.size()) {
            changeList.add(keySet.get(index));
            index++;
        }

//        System.out.println("changeList = " + changeList);

        return changeList;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < 36; i++) {
            dictMap.put(dict[i], i);
        }

        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            int len = input.length;
            for (int j = 0; j < len; j++) {

                BigInteger pow = new BigInteger("36").pow(len-1-j);
                BigInteger zValue = pow.multiply(new BigInteger("35"));
                BigInteger original = pow.multiply(BigInteger.valueOf(dictMap.get(input[j])));

                changedMap.put(input[j], changedMap.getOrDefault(input[j], BigInteger.ZERO).add(zValue.subtract(original)));
                originalMap.put(input[j], originalMap.getOrDefault(input[j], BigInteger.ZERO).add(original));
                conversionMap.put(input[j], conversionMap.getOrDefault(input[j], BigInteger.ZERO).add(zValue));
            }
        }

        K = Integer.parseInt(br.readLine());
    }
}