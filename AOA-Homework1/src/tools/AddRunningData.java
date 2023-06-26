package tools;

import Assignment1.CycleFinding;
import Assignment2.MST;
import jxl.*;
import jxl.write.*;
import jxl.write.Number;
import org.jgrapht.Graph;

import java.io.File;
import java.io.IOException;

public class AddRunningData {

    static WritableWorkbook book;
    static WritableSheet sheet;

    {
        try {
            book = Workbook.createWorkbook(new File("tect.xls"));
//            book = Workbook.createWorkbook(new File("mstTest.xls"));
            sheet = book.createSheet("Assign1", 0);
            Label vertex = new Label(0, 0, "vertex");
            Label edge = new Label(1, 0, "edge");
            Label mPlusn = new Label(2, 0, "mPlusn");
            Label time = new Label(3, 0, "time");
            sheet.addCell(vertex);
            sheet.addCell(edge);
            sheet.addCell(mPlusn);
            sheet.addCell(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addData(RunningTime r, int loopNum){
        try {
            Number vertex = new Number(0,loopNum,r.getVertex());
            Number edge = new Number(1,loopNum,r.getEdge());
            Number mPlusn = new Number(2,loopNum,r.getVertex()+r.getEdge());
            Number time = new Number(3,loopNum,r.getTime());
            sheet.addCell(vertex);
            sheet.addCell(edge);
            sheet.addCell(mPlusn);
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

    public RunningTime getRunningData(int maxSize){
        RunningTime runningTime = new RunningTime();
        CycleFinding cycleFinding = new CycleFinding();
        Generator generator = new Generator(maxSize);
        Graph graph = generator.generateRandomGraph();

        runningTime.setVertex(graph.vertexSet().size());
        runningTime.setEdge(graph.edgeSet().size());

        long startTime = System.currentTimeMillis();
        cycleFinding.ifExistCycle(graph);
        long endTime = System.currentTimeMillis();

        runningTime.setTime(endTime - startTime);

        return runningTime;
    }

    public RunningTime getTimeMST(int maxSize){
        RunningTime runningTime = new RunningTime();
        MST mst=new MST();
        Generator generator = new Generator(maxSize);
        Graph graph = generator.generateRandomSparseGraph();

        runningTime.setVertex(graph.vertexSet().size());
        runningTime.setEdge(graph.edgeSet().size());

        long startTime = System.currentTimeMillis();
        mst.MSTforSparse(graph);
        long endTime = System.currentTimeMillis();

        runningTime.setTime(endTime - startTime);

        return runningTime;
    }

    public static void main(String[] args) {
        AddRunningData addRunningData = new AddRunningData();
        int t=1;
        while(t<=100){
            RunningTime r = addRunningData.getRunningData(3000);
            addRunningData.addData(r, t);
            t++;
        }
        addRunningData.write();
//        AddRunningData addRunningData = new AddRunningData();
//        int t=1;
//        while(t<=300){
//            RunningTime r = addRunningData.getTimeMST(2000);
//            addRunningData.addData(r, t);
//            t++;
//        }
//        addRunningData.write();
    }
}
