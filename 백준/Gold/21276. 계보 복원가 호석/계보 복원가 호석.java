import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder answer = new StringBuilder();
    private static int N, M;
    private static List<Integer>[] adj;
    private static int[] degree;
    private static Map<String, Integer> nameToId = new HashMap<>();
    private static Map<Integer, String> idToName = new HashMap<>();
    private static Map<String, List<String>> child = new HashMap<>();

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        List<String> K = new ArrayList<>();

        Deque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        for (int i = 0; i < N; i++) {

            if (!visited[i] && degree[i] == 0) {
                q.push(i);
                K.add(idToName.get(i));
                visited[i] = true;

                while (!q.isEmpty()) {
                    int current = q.poll();

                    if (adj[current].size() != 0) {
                        for (int next : adj[current]) {
                            degree[next]--;

                            if (degree[next] == 0) {
                                q.offer(next);
                                visited[next] = true;
                                List<String> childs = child.getOrDefault(idToName.get(current), new ArrayList<>());
                                childs.add(idToName.get(next));
                                child.put(idToName.get(current), childs);
                                continue;
                            }
                        }
                    } else {
                        child.put(idToName.get(current), new ArrayList<>());
                    }

                }
            }
        }

        answer.append(K.size()).append("\n");
        Collections.sort(K);
        for (String k : K) {
            answer.append(k).append(" ");
        }
        answer.append("\n");

        List<String> c = new ArrayList<>(child.keySet());
        Collections.sort(c);

        for (String s : c) {
            List<String> cc = child.get(s);
            Collections.sort(cc);
            answer.append(s).append(" ").append(cc.size()).append(" ");
            for (String ss : cc) {
                answer.append(ss).append(" ");
            }
            answer.append("\n");
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N];
        degree = new int[N];

        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String name = st.nextToken();
            nameToId.put(name, i);
            idToName.put(i, name);
        }

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int child = nameToId.get(st.nextToken());
            int parents = nameToId.get(st.nextToken());

            adj[parents].add(child);
            degree[child]++;
        }
    }
}