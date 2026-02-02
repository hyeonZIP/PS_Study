import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int answer = Integer.MIN_VALUE;
    private static String input;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        List<String> expression = splitExpression();
        List<String> result = new ArrayList<>();

        answer = calculate(expression);

        dfs(expression, result);
    }

    private static void dfs(List<String> expression, List<String> result) {
        if (expression.isEmpty()) {
            answer = Math.max(answer, calculate(result));
            return;
        }
        if (expression.size() <= 2) {
            result.addAll(expression);
            // System.out.println("계산할 문자열 : " + result);
            // System.out.println("---------------------------");

            answer = Math.max(answer, calculate(result));
            return;
        }

        List<String> skipResult = new ArrayList<>(result);
        skipResult.add(expression.get(0));
        skipResult.add(expression.get(1));

        dfs(expression.subList(2, expression.size()), skipResult);

        List<String> newExpression = getExpressionExceptParenthesis(expression);
        List<String> newResult = getParenthesisResult(expression, result);
        // System.out.println("괄호로 자르려는 부분 : " + i);
        // System.out.println("자르기 전 : " + expression);
        // System.out.println("자른 후 : " + newExpression);
        // System.out.println("잘라낸 부분 : " + newResult);
        dfs(newExpression, newResult);

    }

    private static List<String> getParenthesisResult(List<String> expression, List<String> result) {
        List<String> newResult = new ArrayList<>(result);

        int num1 = Integer.parseInt(expression.get(0));
        String op1 = expression.get(1);
        int num2 = Integer.parseInt(expression.get(2));

        switch (op1) {
            case "+":
                newResult.add(String.valueOf(num1 + num2));
                break;
            case "-":
                newResult.add(String.valueOf(num1 - num2));
                break;
            case "*":
                newResult.add(String.valueOf(num1 * num2));
                break;
        }

        if (expression.size() > 3) {
            newResult.add(expression.get(3));
        }

        // System.out.println("실제 자른 부분 : " + num1 + op1 + num2);

        return newResult;
    }

    private static List<String> getExpressionExceptParenthesis(List<String> expression) {
        List<String> newExpression = new ArrayList<>();

        for (int i = 0; i < expression.size(); i++) {
            if (i <= 3) {
                continue;
            }

            newExpression.add(expression.get(i));
        }

        return newExpression;
    }

    private static int calculate(List<String> result) {
        Deque<Integer> s = new ArrayDeque<>();

        for (int i = 0; i < result.size(); i++) {
            if (i == 0) {
                s.push(Integer.parseInt(result.get(i)));
                continue;
            } else if (result.get(i).equals("*")) {
                s.push(s.pop() * Integer.parseInt(result.get(i + 1)));
                i++;
                continue;
            } else if (result.get(i).equals("+")) {
                s.push(Integer.parseInt(result.get(i + 1)));
                i++;
                continue;
            } else if (result.get(i).equals("-")) {
                s.push(-Integer.parseInt(result.get(i + 1)));
                i++;
                continue;
            }
        }

        int sum = 0;

        for (int n : s) {
            sum += n;
        }
        // System.out.println("큐 상태 : " + s);
        // System.out.println("계산 결과 : " + sum);
        // System.out.println();

        return sum;
    }

    private static List<String> splitExpression() {
        List<String> expression = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            expression.add(String.valueOf(input.charAt(i)));
        }

        return expression;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = br.readLine();
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}