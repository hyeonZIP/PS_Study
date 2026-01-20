import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder answer = new StringBuilder();
    private static int N, M, Q;
    private static int fileCount;
    private static Set<String> fileType;
    private static String[][] query;
    private static Map<String, List<String>> folderManager = new HashMap<>();
    private static Map<String, List<String>> fileManager = new HashMap<>();

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        for (String[] q : query) {
            fileType = new HashSet<>();
            fileCount = 0;

            String lastFolder = q[q.length - 1];

            dfs(lastFolder);

            answer.append(fileType.size()).append(" ").append(fileCount).append("\n");
        }
    }

    private static void dfs(String folderName) {
        List<String> currentFileList = fileManager.getOrDefault(folderName, new ArrayList<>());
        fileCount += currentFileList.size();
        fileType.addAll(currentFileList);

        for (String folder : folderManager.getOrDefault(folderName, new ArrayList<>())) {
            dfs(folder);
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());// 폴더 개수
        M = Integer.parseInt(st.nextToken());// 파일 개수

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());

            String parent = st.nextToken();// 상위 폴더
            String child = st.nextToken();// 자식 폴더 또는 파일
            int isForder = Integer.parseInt(st.nextToken());// 폴더면 1 아니면 0

            if (isForder == 1) {
                List<String> folderList = folderManager.getOrDefault(parent, new ArrayList<>());
                folderList.add(child);
                folderManager.put(parent, folderList);
            } else {
                List<String> fileList = fileManager.getOrDefault(parent, new ArrayList<>());
                fileList.add(child);
                fileManager.put(parent, fileList);
            }
        }

        Q = Integer.parseInt(br.readLine());

        query = new String[Q][];

        for (int i = 0; i < Q; i++) {
            query[i] = br.readLine().split("/", -1);
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}