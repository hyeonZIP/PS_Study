import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n, d, k, c;
		n = Integer.valueOf(st.nextToken());
		d = Integer.valueOf(st.nextToken());
		k = Integer.valueOf(st.nextToken());
		c = Integer.valueOf(st.nextToken());
		
		int[] sushi = new int[n];
		Map<Integer, Integer> eated = new HashMap<Integer, Integer>();
		for(int i=1; i<=d; i++) eated.put(i, 0);
		for(int i=0; i<n; i++) {
			sushi[i] = Integer.valueOf(br.readLine());
		}
		
		int ans = solution(sushi, eated, n, k, c);
		System.out.println(ans);
	}
	
	public static int solution(int[] sushi, Map<Integer, Integer> eated, int n, int k, int c) {
		int left = 0, right = k-1;
		int ans = 0;
		
		
		for(int i=0; i<k ;i++) {
			eated.put(sushi[i], eated.get(sushi[i]) + 1);
		}
		if(eated.get(c) == 0) eated.put(c, 1);
		
		int cnt = 0;
		for(Integer key : eated.keySet()){
			if(eated.get(key) >= 1) {
				cnt ++;
			}
		}
		if(ans < cnt) ans = cnt;
		
		for(int i=0; i<n; i++) {
			cnt = 0;
			left ++;
			right ++;
			eated.put(sushi[left-1], eated.get(sushi[left-1]) - 1);
			eated.put(sushi[right%n], eated.get(sushi[right%n]) + 1);
			
			eated.put(c, eated.get(c) + 1);
			
			for(Integer key : eated.keySet()){
				if(eated.get(key) >= 1) {
					cnt ++;
				}
			}
			if(ans < cnt) ans = cnt;
		}
		return ans;
	}
}