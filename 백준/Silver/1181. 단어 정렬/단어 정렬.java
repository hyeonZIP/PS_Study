
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<String> s = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			s.add(br.readLine());
		}
		
		Collections.sort(s,new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				
				if(o1.length() == o2.length()) {
					for(int i=0; i<o1.length(); i++) {
						if(o1.charAt(i) - o2.charAt(i) != 0) {
							return o1.charAt(i) - o2.charAt(i);
						}
					}
				}
				
				return o1.length() - o2.length();
			}
		});
		
		s.stream().distinct().forEach(System.out::println);
	}
}
