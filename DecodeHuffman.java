package ALGMethods;
import java.util.HashMap;
import java.util.Scanner;

class DecodeHuffman {

    HashMap<String, Character> map = new HashMap<>();

    private String readHuffmanCode() {
        try (Scanner scanner = new Scanner(System.in)) {
            int lettersCount = scanner.nextInt(); scanner.nextLine();
            for (int i = 0; i < lettersCount; i++) {
                String[] line = scanner.nextLine().split(": ");
                map.put(line[1], line[0].toCharArray()[0]);
            }
            return scanner.nextLine();
        }
    }

    private String decodeHuffman(String str) {
        String result = "", currKey = "";
        for(char c : str.toCharArray()) {
            currKey += c;
            if (map.containsKey(currKey)) {
                result += map.get(currKey); 
                currKey = "";
            }
        }
        return result;
    }

    private void run() {
        System.out.println(decodeHuffman(readHuffmanCode()));
    }

    public static void main(String[] args) {
        new DecodeHuffman().run();
    }
}
