import java.util.Arrays;
import java.util.Scanner;

class Main
{
    static StringBuilder sb = new StringBuilder();
    static int arr[]; // 출력값들이 저장된 arr배열
    static int num[]; // n값이 저장된 배열
    static int n,m;

	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[m];
        num = new int[n];

        for(int i = 0; i < n; i++){
            num[i] = sc.nextInt();
        }
        // 수열은 사전 순으로 증가하는 순서로 출력해야함 그러므로 정렬
        Arrays.sort(num);

        solution(0);

        System.out.println(sb);
    }

    // 비내림차순으로 저장되어야한다. 같은 수를 여러 번 골라도 되니까 파라미터는 depth
    private static void solution(int depth) {
        // depth == m 일 때 배열의 끝까지 간 것임
        if(depth == m){
            for(int num : arr){
                sb.append(num + " ");
            }
            sb.append("\n");
            return;
        }

        // 방금 출력한 값을 뜻하는 before
        int before = -1;
        // 같은 수를 여러 번 골라도 되니까 0부터 시작함
        for(int i = 0; i < n; i++){
            // 방금 배열에 넣은 값인 before와 num[i]가 같다면 continue;
            if(before == num[i]){
                continue;
            }
            // arr인덱스에 현재 값을 넣어준 뒤 recursion
            arr[depth] = before = num[i];
            solution(depth+1);
        }
    }
}