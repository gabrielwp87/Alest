package src.main.utils;

import src.main.domain.types.Stack;

public class StackCopier {

    private StackCopier() {
    }

    ;

    public static Stack copy(Stack stack) {
        Stack cpy = new Stack();
        Stack backup = new Stack();

        while (!stack.isEmpty()) {
            backup.push(stack.pop());
        }

        while (!backup.isEmpty()) {
            char element = backup.pop();
            cpy.push(element);
            stack.push(element);

        }

        return cpy;
    }
}
