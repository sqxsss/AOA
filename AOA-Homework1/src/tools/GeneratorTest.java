package tools;

import org.junit.jupiter.api.Test;
import tools.Generator;

class GeneratorTest {

    @Test
    void getRandomSize() {
        Generator generator = new Generator(20);
        System.out.println(generator.getRandomSize(10, 20));
    }
}