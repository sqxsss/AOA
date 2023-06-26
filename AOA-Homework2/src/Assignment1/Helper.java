package Assignment1;

import java.util.Arrays;
import java.util.Random;

public class Helper {

    public String getDistinctString(String str1, String str2) {
        String s = str1 + str2;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            sb.append(c);
            i++;
            while (i < s.length() && s.charAt(i) == c) {
                i++;
            }
        }
        return sb.toString();
    }

    public int getCount(char c, String str1, String str2) {
        String total = str1 + str2;
        char[] t = total.toCharArray();
        int count = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i] == c) {
                count++;
            }
        }
        return count;
    }

    public int[] getTotalWeight(String str1, String str2, int k) {
        int[] totalW = new int[26];
        char[] t = getDistinctString(str1, str2).toCharArray();
        for(int i=0;i<t.length;i++){
            totalW[t[i]-'A'] = getCount(t[i], str1, str2) * k;
        }
        return totalW;
    }

    public int getRandomD(int[] totalWeight) {
        int[] sort=new int[totalWeight.length];
        for(int k=0;k<totalWeight.length;k++) {
            sort[k] = totalWeight[k];
        }
        Arrays.sort(sort);
        int max = sort[25];
        int min = 0;
        for(int i = 0;i<26;i++){
            if(sort[i] != 0){
                min = sort[i];
                break;
            }
        }
        return new Random().nextInt(max - min + 1) + min;
    }

//    public static void main(String[] args) {
//        Helper implementation = new Helper();
//        int[] t = implementation.getTotalWeight(implementation.s1, implementation.s2, 1);
//        System.out.println(implementation.getRandomD(t));
//    }
}
