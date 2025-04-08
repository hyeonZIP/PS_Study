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
            boolean flag0 = true;

            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0){
                    flag0 = false;
                    break;
                }
            }

            if (flag0){
                char[] arr = N.toCharArray();

                boolean flag1 = true;
                int end = arr.length - 1;

                char[] reverseArr = new char[arr.length];

                for (char c : arr) {
                    int num = Character.getNumericValue(c);
                    if (num == 3 || num == 4 || num == 7) {
                        flag1 = false;
                        break;
                    }

                    switch (num) {
                        case 1, 2, 5, 8, 0:
                            reverseArr[end] = (char) (num + '0');
                            end--;
                            break;
                        case 6:
                            reverseArr[end] = '9';
                            end--;
                            break;
                        case 9:
                            reverseArr[end] = '6';
                            end--;
                    }
                }

                if (flag1) {
                    boolean flag2 = true;
                    long reverseNumber = Long.parseLong(new String(reverseArr));

                    for (int i = 2; i <= Math.sqrt(reverseNumber); i++) {
                        if (reverseNumber % i == 0) {
                            flag2 = false;
                            break;
                        }
                    }

                    if (flag2) {
                        System.out.println("yes");
                    } else {
                        System.out.println("no");
                    }
                } else {
                    System.out.println("no");
                }
            }else{
                System.out.println("no");
            }
        }


    }
}
