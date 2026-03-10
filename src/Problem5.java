import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Problem5 {
    private static final String DIGITS = "0123456789";

    public static void main(String[] args) {
        String input1 = "3[a]2[bc]";
        String input2 = "3[a2[c]]";
        String input3 = "2[abc]3[cd]ef";

        Problem5 instance = new Problem5();

        if (instance.decode(input1).equals("aaabcbc")) {
            System.out.println("Test 1: PASS");
        } else {
            System.out.println("Test 1: FAIL");
            System.out.println(instance.decode(input1) + " vs.\naaabcbc");
        }
        if (instance.decode(input2).equals("accaccacc")) {
            System.out.println("Test 2: PASS");
        } else {
            System.out.println("Test 2: FAIL");
            System.out.println(instance.decode(input1) + " vs.\naccaccacc");
        }
        if (instance.decode(input3).equals("abcabccdcdcdef")) {
            System.out.println("Test 3: PASS");
        } else {
            System.out.println("Test 3: FAIL");
            System.out.println(instance.decode(input1) + " vs.\nabcabccdcdcdef");
        }
    }

    public Problem5() {}

    public String decode(String s) {
        //set up ciphertext for decoding
        char[] ciphertext = s.toCharArray();
        Queue<Character> queue = new LinkedList<>();
        for (char character : ciphertext) {
            queue.add(character);
        }

        //holds plaintext
        StringBuilder plaintext = new StringBuilder();

        //decode ciphertext
        while (!queue.isEmpty()) {
            char character = queue.remove();
            //if a repeating section is found
            if (DIGITS.contains(String.valueOf(character))) {
                //identify the multiplicative integer, k
                Stack<Character> stack = new Stack<>();
                stack.push(character);
                char number = queue.remove();
                while (number != '[') {
                    stack.push(number);
                    number = queue.remove();
                }
                int k = 0;
                int digitsPlace = 1;
                while (!stack.isEmpty()) {
                    k += Character.getNumericValue(stack.pop()) * digitsPlace;
                    digitsPlace *= 10;
                }

                //determine letter sequence to be repeated
                StringBuilder letterSequence = new StringBuilder();
                char letter = queue.remove();
                while (letter != ']') {
                    //if a repeating subsection is found
                    if (DIGITS.contains(String.valueOf(letter))) {
                        //determine the subsection
                        StringBuilder subsection = new StringBuilder();
                        while (letter != ']') {
                            subsection.append(letter);
                            letter = queue.remove();
                        }
                        subsection.append(letter);
                        //decode the subsection and add it to generated letters
                        letterSequence.append(decode(subsection.toString()));
                    } else {
                        letterSequence.append(letter);
                    }
                    letter = queue.remove();
                }

                //generate plaintext section by repeating the letter sequence k times
                for (int iteration = 0; iteration < k; iteration++) {
                    plaintext.append(letterSequence);
                }
            } else {
                //append letter
                plaintext.append(character);
            }
        }
        return plaintext.toString();
    }
}
