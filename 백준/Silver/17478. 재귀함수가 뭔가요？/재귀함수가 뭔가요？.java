import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static final String TEXT= "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.";
	
	static final String[] RF_TEXT = {
			"%s\"재귀함수가 뭔가요?\"\n",
			"%s\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n",
			"%s마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n",
			"%s그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n",
			"%s\"재귀함수는 자기 자신을 호출하는 함수라네\"\n",
			"%s라고 답변하였지.\n"
			};
	
	static final String RF_COUNT_CHECK = "____";
	
	static int N;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		System.out.println(TEXT);
		
		rf(0,"");
	}
	
	private static void rf(int count, String underbar) {
		
		System.out.printf(RF_TEXT[0],underbar);
		
		if(count >= N) {
			System.out.printf(RF_TEXT[4],underbar);
		}
		else {
			System.out.printf(RF_TEXT[1],underbar);
			System.out.printf(RF_TEXT[2],underbar);
			System.out.printf(RF_TEXT[3],underbar);
			rf(count+1,underbar+RF_COUNT_CHECK);
		}
		
		System.out.printf(RF_TEXT[5],underbar);
	}
}
