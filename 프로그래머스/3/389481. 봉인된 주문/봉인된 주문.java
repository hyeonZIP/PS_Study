import java.io.*;
import java.util.*;

class Solution {
    final int ALPHABET = 26;
    
    public String solution(long n, String[] bans) {
        long[] banNumbers = convertStringToNumber(bans);
        
        Arrays.sort(banNumbers);
        
        for(long banNumber : banNumbers){
            if(banNumber <= n){
                n++;
            }
        }
        
        return convertNumberToString(n);
    }
    
    private String convertNumberToString(long n){
        String s = "";
        
        while(n > 0){
            s = (char)((n-1)%ALPHABET + 'a') + s;
            n = (n-1)/ALPHABET;
        }
        
        return s;
    }
    
    private long[] convertStringToNumber(String[] bans){
        long[] banNumbers = new long[bans.length];
        
        for(int i = 0; i<bans.length; i++){
            long num = 0;
            String ban = bans[i];
            
            int banLength = ban.length();
            
            for(int j=0; j<banLength; j++){
                num += (ban.charAt(j)-'a'+1) * Math.pow(ALPHABET,banLength - 1 - j);
            }
            
            banNumbers[i] = num;
        }

        return banNumbers;
    }
}