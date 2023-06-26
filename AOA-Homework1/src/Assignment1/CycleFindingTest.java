package Assignment1;

import org.jgrapht.Graph;
import org.jgrapht.generate.CompleteGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import tools.Generator;

import java.util.function.Supplier;

class CycleFindingTest {

    private CycleFinding cycleFinding = new CycleFinding();

    @Test
    void ifExistCycleTest1() {
        Generator generator = new Generator(10);
        Graph graph = generator.generateRandomGraph();

        long startTime = System.nanoTime();    //获取开始时间
        boolean result = cycleFinding.ifExistCycle(graph);
        long endTime = System.nanoTime();    //获取结束时间
        System.out.println("running time：" + (endTime - startTime) + "ns");    //输出程序运行时间
    }

    @Test
    void ifExistCycleTest2() {
        Generator generator = new Generator(20);
        Graph graph = generator.generateRandomGraph();

        long startTime = System.nanoTime();    //获取开始时间
        boolean result = cycleFinding.ifExistCycle(graph);
        long endTime = System.nanoTime();    //获取结束时间
        System.out.println("running time：" + (endTime - startTime) + "ns");    //输出程序运行时间
    }

    @Test
    void ifExistCycleTest3() {
        Generator generator = new Generator(30);
        Graph graph = generator.generateRandomGraph();

        long startTime = System.nanoTime();    //获取开始时间
        boolean result = cycleFinding.ifExistCycle(graph);
        long endTime = System.nanoTime();    //获取结束时间
        System.out.println("running time：" + (endTime - startTime) + "ns");    //输出程序运行时间
    }

    @Test
    void ifExistCycleTest4() {
        Generator generator = new Generator(40);
        Graph graph = generator.generateRandomGraph();

        long startTime = System.nanoTime();    //获取开始时间
        boolean result = cycleFinding.ifExistCycle(graph);
        long endTime = System.nanoTime();    //获取结束时间
        System.out.println("running time：" + (endTime - startTime) + "ns");    //输出程序运行时间
    }

    @Test
    void ifExistCycleTest5() {
        Generator generator = new Generator(200);
        Graph graph = generator.generateRandomGraph();

        long startTime = System.nanoTime();    //获取开始时间
        boolean result = cycleFinding.ifExistCycle(graph);
        long endTime = System.nanoTime();    //获取结束时间
        System.out.println("running time：" + (endTime - startTime) + "ns");    //输出程序运行时间
    }

    public Graph testGenerator(int size) {
        Supplier<Integer> vSupplier = new Supplier<Integer>() {
            private int id = 0;

            @Override
            public Integer get() {
                return id++;
            }
        };

        Graph<Integer, DefaultEdge> completeGraph =
                new SimpleGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), false);

        CompleteGraphGenerator<Integer, DefaultEdge> completeGenerator =
                new CompleteGraphGenerator<>(size);

        completeGenerator.generateGraph(completeGraph);
        return completeGraph;
    }

    @Test
    void ifExistCycleTest6() { //m > n, a complete graph
        Graph graph = testGenerator(6);
        boolean result = cycleFinding.ifExistCycle(graph);
        Assert.assertEquals(result, true);
    }

    @Test
    void ifExistCycleTest7() { //m > n
        Graph graph = testGenerator(6);
        graph.removeEdge(0,1);
        graph.removeEdge(3,5);
        graph.removeEdge(0,4);
        graph.removeEdge(2,5);
        boolean result = cycleFinding.ifExistCycle(graph);
        Assert.assertEquals(result, true);
    }

    @Test
    void ifExistCycleTest8() { //m = n, vertex 0 is no degree
        Graph graph = testGenerator(4);
        graph.removeEdge(0,1);
        graph.removeEdge(0,2);
        graph.removeEdge(0,3);
        boolean result = cycleFinding.ifExistCycle(graph);
        Assert.assertEquals(result, true);
    }

    @Test
    void ifExistCycleTest9() { //m < n
        Graph graph = testGenerator(5);
        graph.removeEdge(0,1);
        graph.removeEdge(0,2);
        graph.removeEdge(0,3);
        graph.removeEdge(1,4);
        graph.removeEdge(2,4);
        graph.removeEdge(3,4);
        boolean result = cycleFinding.ifExistCycle(graph);
        Assert.assertEquals(result, true);
    }

    @Test
    void ifExistCycleTest10() { //m < n
        Graph graph = testGenerator(3);
        graph.removeEdge(0,1);
        boolean result = cycleFinding.ifExistCycle(graph);
        Assert.assertEquals(result, false);
    }
}