import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Problem4 {
    public static void main(String[] args) {
        String test1 = "{[()]}";
        String test2 = "{[(])}¿";
        String test3 = "{{[[(()<>)]]}}";

        Problem4 instance = new Problem4();

        System.out.println("Expecting: YES, NO, YES");
        System.out.println(instance.isBalanced(test1));
        System.out.println(instance.isBalanced(test2));
        System.out.println(instance.isBalanced(test3));
    }

    private Map<Character, Character> allowedBrackets = new HashMap<>();

    public Problem4() {
        allowedBrackets.put('{', '}');
        allowedBrackets.put('(', ')');
        allowedBrackets.put('[', ']');
        allowedBrackets.put('<', '>');
        allowedBrackets.put('¿', '?');
    }

    public String isBalanced(String s) {
        char[] inputArray = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char character : inputArray) {
            if (!allowedBrackets.containsKey(character) && !allowedBrackets.containsValue(character)) {
                throw new IllegalArgumentException("Does not contain only acceptable bracket characters.");
            }
            if (allowedBrackets.containsKey(character)) {
                stack.push(allowedBrackets.get(character));
            } else if (stack.pop() != character) {
                return "NO";
            }
        }
        return "YES";
    }
}
