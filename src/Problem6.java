import java.util.LinkedList;
import java.util.Queue;

public class Problem6 {
    public static void main(String[] args) {
        String input = "a+b*(c^d-e)^(f+g*h)-i";
    }

    public String convertInfix(String infix) {
        //holds postfix
        StringBuilder postfix = new StringBuilder();

        //convert infix
        char[] infixCharacters = infix.toCharArray();
        Queue<Character> queue = new LinkedList<>();
        for (char character : infixCharacters) {
            queue.add(character);
        }
        while (!queue.isEmpty()) {
            char character = queue.remove();
            //prioritize operations within parentheses

            //prioritize ^ operations, ignoring right associativity

        }


        return postfix.toString();
    }
}
