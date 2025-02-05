import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static final String TEXT= "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.";
	
	static final String RF_HEADER_TEXT = "\"재귀함수가 뭔가요?\"";
	
	static final String RF_BODY_TEXT1 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.";
	static final String RF_BODY_TEXT2 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.";
	static final String RF_BODY_TEXT3 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
	
	static final String RF_FOOTER_TEXT = "라고 답변하였지.";
	
	static final String RF_STOP_TEXT = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
	
	static final String RF_COUNT_CHECK = "____";
	
	static int N;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		System.out.println(TEXT);
		
		rf(0);
	}
	

	public static void rf(int count) {
		
		printRFCount(count);
		
		System.out.println(RF_HEADER_TEXT);//재귀함수가 뭔가요
		
		/**
		 * 잘 들어보게... or 재귀함수는 자기자신을...
		 */
		if(count >= N) {
			printRFCount(count);
			System.out.println(RF_STOP_TEXT);
			printRFCount(count);
			System.out.println(RF_FOOTER_TEXT);
			return;
		}
		else {
			printRFCount(count);
			System.out.println(RF_BODY_TEXT1);
			printRFCount(count);
			System.out.println(RF_BODY_TEXT2);
			printRFCount(count);
			System.out.println(RF_BODY_TEXT3);
		}
		rf(count+1);
		
		printRFCount(count);
		System.out.println(RF_FOOTER_TEXT);
	}
	
	private static void printRFCount(int count) {
		for(int i=0; i<count; i++) {
			System.out.print(RF_COUNT_CHECK);
		}
	}
}
