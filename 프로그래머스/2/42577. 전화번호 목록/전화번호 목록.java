import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        HashMap<String, Integer> phoneBook = new HashMap<String,Integer>();
        
        for(String phoneNumber : phone_book){
            phoneBook.put(phoneNumber, 0);
        }
        
        for(String phoneNumber : phoneBook.keySet()){
            for(int index = 1; index<phoneNumber.length(); index++){
                if(phoneBook.containsKey(phoneNumber.substring(0,index))){
                    return false;
                }
            }
        }
        return true;
    }
}