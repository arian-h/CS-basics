package algorithm.greedy;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class HuffmanCodingMachineUT {

    @Test
    public void testMachine() {
        HuffmanCodingMachine machine = IHuffmanCodingMachine.getInstance(new HashMap<Character, Double>() {{
            put('r', 0.1);
            put('a', 0.2);
            put('y', 0.4);
            put('n', 0.5);
        }});
        testCodeAndEncode(machine, "yarayarayaraaryanaryanyararyan");
        testCodeAndEncode(machine, "yarayan");
        testCodeAndEncode(machine, "aryanaaaar");
    }

    private void testCodeAndEncode(IHuffmanCodingMachine machine, String text) {
        Assert.assertEquals(text, machine.decode(machine.encode(text)));
    }
}
