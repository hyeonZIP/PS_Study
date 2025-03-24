import java.io.*;
import java.util.*;

public class Main {
	
	static int Q;
	static long answer;
	static HashMap<String,PriorityQueue<Integer>> map = new HashMap<>();
	
	public static void main(String[] args)throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Q = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int cmd = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			int k = Integer.parseInt(st.nextToken());
			PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o->-o));
			
			if(cmd == 1) {
				for(int ii=0; ii<k; ii++) {
					pq.offer(Integer.parseInt(st.nextToken()));
				}
				
				if(map.containsKey(name)) {
					map.get(name).addAll(pq);
				}else {
					map.put(name, pq);
				}
			} else {
				if(map.containsKey(name)) {
					pq = map.get(name);
					
					if(pq.isEmpty()) continue;
					
					if(k > pq.size()) k = pq.size();
					
					for(int ii=0; ii<k; ii++) {

						answer += pq.poll();
					}
				}
				
				
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(answer+"");
		bw.flush();
		bw.close();
		br.close();
		
	}
}
