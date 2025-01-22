class Solution {
    int[] arr;
    int targetNum = 0;
    int answer = 0;
    public int solution(int[] numbers, int target) {
        arr = new int[numbers.length];
        targetNum = target;

        for (int i=0; i<numbers.length; i++){
            arr[i] = numbers[i];
        }

        dfs(arr[0],1);
        dfs(-1 * arr[0],1);

        return answer;
    }

    public void dfs(int number, int index){
        if (number == targetNum && index == arr.length){
            System.out.println("test");
            answer++;
            return;
        }

        if (index >= arr.length){
            return;
        }
        dfs(number-arr[index], index+1);
        dfs(number+arr[index], index+1);
    }
}