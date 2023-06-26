package Assignment1;

import jxl.*;
import jxl.write.*;
import jxl.write.Number;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class AddRunningData {

    static WritableWorkbook book;
    static WritableSheet sheet;

    {
        try {
            book = Workbook.createWorkbook(new File("test1.xls"));
//            book = Workbook.createWorkbook(new File("mstTest.xls"));
            sheet = book.createSheet("Assign1", 0);
            Label M = new Label(0, 0, "M");
            Label N = new Label(1, 0, "N");
            Label MN = new Label(2, 0, "M*N");
            Label time = new Label(3, 0, "time");
            sheet.addCell(M);
            sheet.addCell(N);
            sheet.addCell(MN);
            sheet.addCell(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addData(RunningTime r, int loopNum) {
        try {
            Number M = new Number(0, loopNum, r.getM());
            Number N = new Number(1, loopNum, r.getN());
            Number MN = new Number(2, loopNum, r.getM() * r.getN());
            Number time = new Number(3, loopNum, r.getTime());
            sheet.addCell(M);
            sheet.addCell(N);
            sheet.addCell(MN);
            sheet.addCell(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write() {
        try {
            book.write();
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generateString(int maxSize) {
        int length = new Random().nextInt(maxSize) + 1;
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(26);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public RunningTime getRunningData(int maxSize) {
        RunningTime runningTime = new RunningTime();
        WASC wasc = new WASC();
        String s1 = generateString(maxSize);
        String s2 = generateString(maxSize);

        runningTime.setM(s1.length());
        runningTime.setN(s2.length());

//        System.out.println(s1);
//        System.out.println(s2);

        wasc.getWeightT(s1, s2);
        wasc.getDelta();

        long startTime = System.nanoTime();
//        System.out.println(wasc.OPT(s1, s2));
        wasc.OPT(s1, s2);
        long endTime = System.nanoTime();

        runningTime.setTime(endTime - startTime);

        return runningTime;
    }

    public static void main(String[] args) {
        AddRunningData addRunningData = new AddRunningData();
//        addRunningData.getRunningData(20);
        int t = 1;
        while (t <= 500) {
            RunningTime r = addRunningData.getRunningData(200);
            addRunningData.addData(r, t);
            t++;
        }
        addRunningData.write();

    }
}
