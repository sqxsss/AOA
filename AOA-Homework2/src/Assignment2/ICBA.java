package Assignment2;

import java.util.HashMap;
import java.util.Map;

public class ICBA {

    public CountHelper countHelper = new CountHelper();

    public double[][] getAllErr(double[] y) {
        double[][] err = new double[y.length][y.length];
        for (int i = 0; i < y.length; i++) {
            for (int j = 0; j < y.length; j++) {
                err[i][j] = countHelper.err(y, i, j);
            }
        }
        return err;
    }

    public HashMap<String, Double> getAllErrHash(double[] y) {
        HashMap<String, Double> err = new HashMap<>();
        for (int i = 0; i < y.length; i++) {
            for (int j = 0; j < y.length; j++) {
                err.put(i + "" + j, countHelper.err(y, i, j));
            }
        }
        return err;
    }

    public void OPT(int[] x, double[] y, double delta) {
        double M[] = new double[x.length];     //memorize the opt(i) for each x.
        M[0] = 0;
        double[][] err = getAllErr(y);
        int[] index_err = new int[x.length];
        for (int j = 1; j < x.length; j++) {
            double temp = err[0][j] + delta;
            for (int i = 1; i <= j; i++) {
                if (err[i][j] + M[i - 1] + delta < temp) {
                    index_err[j] = i;
                    temp = err[i][j] + M[i - 1] + delta;
                }
            }
            M[j] = temp;
        }

        getInterval(index_err);
    }

    public void OPTHash(int[] x, double[] y, double delta) {
        double M[] = new double[x.length];    //memorize the opt(i) for each x.
        M[0] = 0;
        HashMap<String, Double> err = getAllErrHash(y);
        int[] index_err = new int[x.length];
        for (int j = 1; j < x.length; j++) {
            double temp = err.get(0 + "" + j) + delta;
            for (int i = 1; i <= j; i++) {
                if (err.get(i + "" + j) + M[i - 1] + delta < temp) {
                    index_err[j] = i;
                    temp = err.get(i + "" + j) + M[i - 1] + delta;
                }
            }
            M[j] = temp;
        }
        getInterval(index_err);
    }

    public void getInterval(int[] index) {
        for (int z = 0; z < index.length - 1; z++) {
            if (index[z] != index[z + 1]) {
                System.out.print(index[z] + ",");
            }
//            System.out.print(index[z]+",");
        }
        System.out.print(index.length - 1);
        System.out.println();
    }

    public static void main(String[] args) {
        ICBA icba = new ICBA();
        int[] x = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double[] y = {1, 2, 3, 6, 7, 8, 14, 14.5, 14.8, 15};
        double delta = 100;
        Runtime r = Runtime.getRuntime();
        r.gc();
        long startMem = r.freeMemory();
//        icba.OPTHash(x, y, delta);
        icba.OPT(x, y, delta);
        long endMem = r.freeMemory();
        long orz = startMem - endMem;

        System.out.println("memory condition:");
        System.out.println("Start: " + startMem);
        System.out.println("End: " + endMem);
        System.out.println("Cost: " + orz);
    }
}