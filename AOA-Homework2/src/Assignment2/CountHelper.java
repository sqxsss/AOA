package Assignment2;

public class CountHelper {

    public double sqSum(double[] ds, int i, int j) {
        double s = 0;
        for (int k = i; k <= j; k++) {
            s = (s + Math.pow(ds[k], 2));
        }
        return s;
    }

    public double sum(double[] ds, int i, int j) {
        double s = 0;
        for (int k = i; k <= j; k++) {
            s = s + ds[k];
        }
        return s;
    }

    public double err(double[] ds, int i, int j) {
        double s = sqSum(ds, i, j) + Math.pow(sum(ds, i, j), 2) / ds.length;
        return s;
    }
}
