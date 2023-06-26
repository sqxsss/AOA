package Assignment1;

import java.util.Scanner;

public class WASC {

    Helper helper = new Helper();

    static int[] totalWeight = new int[26];

    static int delta;

    static int[][] dp;

    public int OPT(String str1, String str2) {
        char[] a = str1.toCharArray();
        char[] b = str2.toCharArray();
        dp = new int[a.length][b.length];

        for(int k = 0;k<a.length;k++){
            if(str1.substring(0,k+1).indexOf(b[0]) != -1){
                dp[k][0] = totalWeight[b[0]-'A'];
            } else {
                dp[k][0] = 0;
            }
        }
        for(int l = 0;l<b.length;l++){
            if(str2.substring(0,l+1).indexOf(a[0]) != -1){
                dp[0][l] = totalWeight[a[0]-'A'];
            } else {
                dp[0][l] = 0;
            }
        }

        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < b.length; j++) {
                if (a[i] == b[j]) {
                    int[] temp = transform(str1.substring(0,i+1),str2.substring(0,j+1));
                    dp[i][j] = MSubA(temp);
                } else {
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
//
//        for(int i=0; i<a.length; i++){
//            for (int j=0; j<b.length; j++)
//                System.out.printf("%5d",dp[i][j]);
//            System.out.println();
//        }

        int maxn= Integer.MIN_VALUE;
        int xend = 0;
        int yend = 0;
        for(int x=0;x<a.length;x++) {
            for(int y=0;y<b.length;y++) {
                if(dp[x][y]>=maxn) {
                    maxn = Math.max(maxn, dp[x][y]);
                    xend = x;
                    yend = y;
                }
            }
        }
        while(xend>=1 && yend>=1) {
            if(dp[xend][yend] == dp[xend-1][yend-1]){
                xend = xend-1;
                yend = yend-1;
            } else {
                break;
            }
        }
//        System.out.println(xend);
        System.out.println(getSubString(maxn, xend, yend, str1, str2));
        return maxn;
    }

    public String getSubString(int max, int xend, int yend, String str1, String str2) {
        int[] t = transform(str1.substring(0,xend+1),str2.substring(0,yend+1));
        int length=0;
        for(int i = t.length-1; i>=0;i--){
            max=max-t[i];
            if(max==0){
                length = t.length-i;
                break;
            }
        }
//        System.out.println(length);
        return str1.substring(xend-length+1,xend+1)+ "," + str2.substring(yend-length+1,yend+1);
    }

    public int MSubA(int[] w) {
        int[] dp = new int[w.length];
        dp[0] = w[0];

        int max = dp[0];
        int start = 0;
        int end = 0;
        for(int i = 1; i < dp.length; i++){
            if(dp[i - 1] > 0) {
                dp[i] = w[i] + dp[i - 1];
            } else {
                dp[i] = w[i];
                start = i;
            }
            if(max<=dp[i]){
                max = Math.max(max, dp[i]);
                end = i;
            }
//            max = Math.max(max, dp[i]);
        }
//        System.out.println(start);
//        System.out.println(end);
        return max;
    }

    public void getWeightT(String str1, String str2) {
        int k = 1;
        totalWeight = helper.getTotalWeight(str1, str2, k);
        System.out.println("wi = k*x, k = " + k);
//        for(int i =0;i<totalWeight.length;i++){
//            totalWeight[i] = 1;
//        }
    }

    public void getDelta() {
//        delta = 10;
        delta = helper.getRandomD(totalWeight);
    }

    public int[] transform(String s1, String s2) {
        int[] trans = new int[Math.min(s1.length(), s2.length())];
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        if (s1.length() >= s2.length()) {
            for (int i = chars2.length - 1; i >= 0; i--) {
                if (chars1[i+s1.length()- s2.length()] == chars2[i]) {
                    trans[i] = totalWeight[chars2[i] - 'A'];
                } else {
                    trans[i] = -1 * delta;
                }
            }
        } else {
            for (int j = chars1.length - 1; j >= 0; j--) {
                if (chars2[j+s2.length()-s1.length()] == chars1[j]) {
                    trans[j] = totalWeight[chars1[j] - 'A'];
                } else {
                    trans[j] = -1 * delta;
                }
            }
        }
        return trans;
    }

    public static void main(String[] args) {
//        String s1 = "ABCAABCAA";
//        String s2 = "ABBCAACCBBBBBB";

        String s1 = "";
        String s2 = "";

        Scanner sc = new Scanner(System.in);
        s1 = sc.next();
        s2 = sc.next();

        WASC wasc = new WASC();
        wasc.getWeightT(s1, s2);
        wasc.getDelta();
        System.out.println("delta = " + delta);

//        long startTime = System.nanoTime();    //获取开始时间
//        System.out.println(wasc.OPT(s1,s2));
//        long endTime = System.nanoTime();    //获取结束时间

        long startTime = System.currentTimeMillis();
        System.out.println("Maximum weight = " + wasc.OPT(s1,s2));
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }
}
