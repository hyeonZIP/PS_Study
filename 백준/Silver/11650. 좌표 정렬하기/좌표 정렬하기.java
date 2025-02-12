
import java.util.*;
import java.io.*;

public class Main {
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<int[]> list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list.add(new int[] {x,y});
		}
		
		Collections.sort(list,new Comparator<>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				
				if(o1[0]==o2[0]) {
					return o1[1]-o2[1];
				}
				
				return o1[0]-o2[0];
			}
		});
		
		list.stream().forEach(o -> System.out.println(o[0] + " "+ o[1]));
	}
}
