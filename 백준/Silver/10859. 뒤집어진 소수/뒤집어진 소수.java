import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();
        long number = Long.parseLong(N);

        if (number == 1){
            System.out.println("no");
        }else{
            if (isPrime(number)){

                char[] arr = N.toCharArray();

                char[] reversedArr = isReversible(arr);
                if (reversedArr.length == 0) {
                    System.out.println("no");
                } else {
                    long reverseNumber = Long.parseLong(new String(reversedArr));
                    if (isPrime(reverseNumber)) {
                        System.out.println("yes");
                    } else {
                        System.out.println("no");
                    }
                }
            }else{
                System.out.println("no");
            }
        }
    }

    private static char[] isReversible(char[] arr){
        int end = arr.length-1;
        char[] reversedArr = new char[arr.length];

        for (char c : arr) {
            int num = Character.getNumericValue(c);
            if (num == 3 || num == 4 || num == 7) {
                return new char[0];
            }

            switch (num) {
                case 1, 2, 5, 8, 0:
                    reversedArr[end] = (char) (num + '0');
                    end--;
                    break;
                case 6:
                    reversedArr[end] = '9';
                    end--;
                    break;
                case 9:
                    reversedArr[end] = '6';
                    end--;
                    break;
                default:
                    
            }
        }

        return reversedArr;
    }

    private static boolean isPrime(long number){
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0){
                return false;
            }
        }
        return true;
    }
}
