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

    private static final String FIRST_BRACKETS = "{([<¿";
    private static final String SECOND_BRACKETS = "})]>?";

    public Problem4() {}

    public String isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        for (char character : s.toCharArray()) {
            if (FIRST_BRACKETS.contains(String.valueOf(character))) {
                stack.push(character);
            } else if (SECOND_BRACKETS.contains(String.valueOf(character))) {
                if (returnPosition(stack.pop(), FIRST_BRACKETS.toCharArray()) != returnPosition(character, SECOND_BRACKETS.toCharArray())) {
                    return "NO";
                }
            }
        }
        return "YES";
    }

    private int returnPosition(char character, char[] array) {
        for (int index = 0; index < array.length; index++) {
            if (character == array[index]) {
                return index;
            }
        }
        return -1;
    }
}