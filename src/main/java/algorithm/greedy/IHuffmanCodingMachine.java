package algorithm.greedy;

import java.util.Map;

public interface IHuffmanCodingMachine {

    /**
     * Encode a text using huffman coding algorithm
     * @param toEncode
     * @return
     */
    String encode(String toEncode);

    /**
     * Decode a text, using huffman coding algorithm
     * @param encodedText
     * @return
     */
    String decode(String encodedText);

    static HuffmanCodingMachine getInstance(Map<Character, Double> charProbability) {
        return HuffmanCodingMachine.getInstance(charProbability);
    }

}
