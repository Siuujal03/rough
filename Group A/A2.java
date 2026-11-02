import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class HuffmanNode {
    int frequency;
    char character;
    HuffmanNode left;
    HuffmanNode right;
}

class NodeComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.frequency - y.frequency;
    }
}

class HuffmanEncoding {

    public static void printCodes(HuffmanNode root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.character, code);
            System.out.println(root.character + " : " + code);
            return;
        }

        printCodes(root.left, code + "0", huffmanCodes);
        printCodes(root.right, code + "1", huffmanCodes);
    }

    public static void buildHuffmanTree(char[] charArray, int[] charFreq) {
        int n = charArray.length;
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(n, new NodeComparator());

        for (int i = 0; i < n; i++) {
            HuffmanNode node = new HuffmanNode();
            node.character = charArray[i];
            node.frequency = charFreq[i];
            node.left = null;
            node.right = null;
            pq.add(node);
        }

        HuffmanNode root = null;

        while (pq.size() > 1) {
            HuffmanNode x = pq.peek();
            pq.poll();

            HuffmanNode y = pq.peek();
            pq.poll();

            HuffmanNode f = new HuffmanNode();
            f.frequency = x.frequency + y.frequency;
            f.character = '-';
            f.left = x;
            f.right = y;

            root = f;
            pq.add(f);
        }

        Map<Character, String> huffmanCodes = new HashMap<>();
        System.out.println("Generated Huffman Codes:");
        printCodes(root, "", huffmanCodes);
    }

    public static void main(String[] args) {
        System.out.println("=== Static Verification Test ===");
        char[] staticChars = {'a', 'b', 'c', 'd', 'e', 'f'};
        int[] staticFreqs = {5, 9, 12, 13, 16, 45};

        System.out.println("Characters and Frequencies:");
        for (int i = 0; i < staticChars.length; i++) {
            System.out.println(staticChars[i] + ": " + staticFreqs[i]);
        }
        buildHuffmanTree(staticChars, staticFreqs);

        System.out.println("\n=== User Input Mode ===");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of unique characters: ");
        int numChars = scanner.nextInt();

        char[] userChars = new char[numChars];
        int[] userFreqs = new int[numChars];

        for (int i = 0; i < numChars; i++) {
            System.out.print("Enter character " + (i + 1) + ": ");
            userChars[i] = scanner.next().charAt(0);
            System.out.print("Enter frequency for " + userChars[i] + ": ");
            userFreqs[i] = scanner.nextInt();
        }

        buildHuffmanTree(userChars, userFreqs);

        scanner.close();
    }
}