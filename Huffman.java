package ALGMethods;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Huffman {

    HashMap<Character, String> codes = new HashMap<>();

    private HashMap<Character, Integer> countOccurances(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) map.put(c, map.containsKey(c) ? map.get(c)+1 : 1);
        return map;
    }

    private List<Node> leafsFromMap(Map<Character, Integer> map) {
        return map.keySet().stream().map(c -> new Node(c, map.get(c))).collect(Collectors.toList());
    }

    private Node huffman(List<Node> leafs) {
        PriorityQueue<Node> queue = new PriorityQueue<>(leafs);
        while (queue.size() > 1) queue.add(new Node(queue.poll(), queue.poll()));
        return queue.poll();
    }

    private void getCodes(Node root, String code) {
        code += (root.getStatus() == NodeStatus.ROOT ? "" : root.getSide().ordinal() );
        if (root.getStatus() == NodeStatus.LEAF) codes.putIfAbsent(root.getLetter(), code); 
        else {
            getCodes(root.getLeft(), code);
            getCodes(root.getRight(), code);
        }
    }

    private String codeString(String str) {
        StringBuilder result = new StringBuilder();
        for (Character c : str.toCharArray()) result.append(codes.get(c));
        return result.toString();
    }

    private void run() {
        try (Scanner scnr = new Scanner(new File("input.txt"))) {
            String str = scnr.nextLine();
            getCodes(huffman(leafsFromMap(countOccurances(str))), "");
            str = codeString(str);
            System.out.println(codes.size() + " " + str.length());
            for (Character c : codes.keySet()) System.out.println(c + ": " + codes.get(c));
            System.out.println(str);
        } catch (Exception e) { }
    }


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new Huffman().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    enum NodeSide { LEFT, RIGHT; }

    enum NodeStatus { LEAF, INNER, ROOT; }

    class Node implements Comparable<Node> {
        private       NodeStatus status;
        private       NodeSide   side;
        private final Node left, right;
        private final Character  letter;
        private final int        value;

        private Node(NodeStatus status, NodeSide side, Node left, Node right, Character letter, int value) {
            this.status = status; this.left = left;   this.right = right; 
            this.letter = letter; this.value = value; this.side = side;
        }

        public Node(Character letter, int value) {
            this(NodeStatus.LEAF, NodeSide.LEFT, null, null, letter, value);
        }

        public Node(Node left, Node right) {
            this(NodeStatus.ROOT, null, left, right, null, left.getValue() + right.getValue());
            left.setSide(NodeSide.LEFT); right.setSide(NodeSide.RIGHT);
            if (left.getStatus() == NodeStatus.ROOT) left.setStatus(NodeStatus.INNER);
            if (right.getStatus() == NodeStatus.ROOT) right.setStatus(NodeStatus.INNER);
        }

        public void       setSide(NodeSide side)       { this.side = side; }
        public void       setStatus(NodeStatus status) { this.status = status; }
        public NodeStatus getStatus()                  { return status; } 
        public NodeSide   getSide()                    { return side; }
        public Node       getLeft()                    { return left; }
        public Node       getRight()                   { return right; }
        public Character  getLetter()                  { return letter; }
        public int        getValue()                   { return value; }

        @Override 
        public int compareTo(Node node) {
            return Integer.compare(this.getValue(), node.getValue());
        }

    }
}
