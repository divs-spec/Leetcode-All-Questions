import java.util.*;
class Solution {
    List<List<String>> menu = new ArrayList<>();

    void intialize_menu() {
        for (int i = 0; i <= 9; i++) {
            List<String> x = new ArrayList<>();
            menu.add(x);
        }

        menu.get(2).add("a");
        menu.get(2).add("b");
        menu.get(2).add("c");

        menu.get(3).add("d");
        menu.get(3).add("e");
        menu.get(3).add("f");

        menu.get(4).add("g");
        menu.get(4).add("h");
        menu.get(4).add("i");

        menu.get(5).add("j");
        menu.get(5).add("k");
        menu.get(5).add("l");

        menu.get(6).add("m");
        menu.get(6).add("n");
        menu.get(6).add("o");

        menu.get(7).add("p");
        menu.get(7).add("q");
        menu.get(7).add("r");
        menu.get(7).add("s");

        menu.get(8).add("t");
        menu.get(8).add("u");
        menu.get(8).add("v");

        menu.get(9).add("w");
        menu.get(9).add("x");
        menu.get(9).add("y");
        menu.get(9).add("z");
    }

   
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<String>();
        if (digits.length() ==0) {
            return ans;
        }
        intialize_menu();
        backtrack(ans, digits, 0, new StringBuilder());
        return ans;
    }
     void backtrack(List<String> ans, String digits, int index, StringBuilder path) {
        // Base case: if the path length equals digits length, add to answer
        if (index == digits.length()) {
            ans.add(path.toString());
            return;
        }

        int digit = digits.charAt(index) - '0';
        List<String> letters = menu.get(digit);
        for (String letter : letters) {
            path.append(letter); // choose
            backtrack(ans, digits, index + 1, path); // explore
            path.deleteCharAt(path.length() - 1); // un-choose (backtrack)
        }
    }

}
