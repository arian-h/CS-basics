package algorithm.greedy;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCodingMachine implements IHuffmanCodingMachine {

    private final static double epsilon = 0.0001d;
    private final Map<Character, Double> charProbability;
    private final Map<Character, String> codeMap;
    private Node root;

    /**
     * The idea of huffman coding is to create a binary tree, bottom up, according to the probability of seeing each
     * character in a text. Basically it assigns shorter codes to more frequent symbols, and longer codes to the
     * less frequent symbols.
     *
     * In order to achieve this goal, it uses Min Priority Queue based on the frequency of each symbol.
     * It polls two nodes at each time, and create a parent for them with a frequency equal to the frequency of its
     * children. It continues doing this until it gets to one node in queue, which would be the root.
     *
     * By traversing the tree, we'll have the coding map.
     *
     * To encode the coding map (which can be created using dfs on the tree) is required.
     *
     * The decode the tree itself is required.
     *
     * This implementation assumes that there are at least 2 symbols in the given language.
     *
     * @param charProbability
     */
    public HuffmanCodingMachine(Map<Character, Double> charProbability) {
        // create Huffman coding tree
        this.charProbability = charProbability;
        this.root = createTree();
        this.codeMap = new HashMap<>();
    }

    private void dfs(Node root, StringBuilder code, int dir) {
        code.append(dir);
        if (root.left == null && root.right == null) {
            codeMap.put(root.value, code.toString().substring(1));
        } else {
            dfs(root.left, code, 0);
            dfs(root.right, code, 1);
        }
        code.deleteCharAt(code.length() - 1);
    }

    private Node createTree() {
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> {
            if (Math.abs(n1.probability - n2.probability) < epsilon) {
                return 0;
            }
            if (n1.probability - n2.probability < 0) {
                return -1;
            }
            return 1;
        });
        for (Character c: charProbability.keySet()) {
            Node n = new Node(charProbability.get(c));
            n.value = c;
            queue.offer(n);
        }
        while (queue.size() > 1) {
            Node node1 = queue.poll();
            Node node2 = queue.poll();
            Node root = new Node(node1.probability + node2.probability);
            root.left = node1;
            root.right = node2;
            queue.offer(root);
        }
        return queue.poll();
    }

    public String encode(String toEncode) {
        StringBuilder encodedText = new StringBuilder();
        for (char c: toEncode.toCharArray()) {
            encodedText.append(codeMap.get(c));
        }
        return encodedText.toString();
    }

    public String decode(String text) {
        StringBuilder decodedText = new StringBuilder();
        Node current = root;
        for (char c: text.toCharArray()) {
            int i = c - '0';
            if (i == 0) {
                current = current.left;
            } else {
                current = current.right;
            }
            if (current.left == null && current.right == null) {
                decodedText.append(current.value);
                current = root;
            }
        }
        return decodedText.toString();
    }

    @Override
    public void build() {
        root = createTree();
        //do a dfs and create coding map
        dfs(root, new StringBuilder(), 0);
    }

    private static class Node {
        private char value;
        private final double probability;
        private Node left, right;
        private Node(double probability) {
            this.probability = probability;
        }
    }
}
