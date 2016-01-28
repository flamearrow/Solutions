package letterCombinationsOfAPhoneNumber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//Given a digit string, return all possible letter combinations that the number could represent.
//
//A mapping of digit to letters (just like on the telephone buttons) is given below.
//Input:Digit string "23"
//Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
//
//Note:
//Although the above answer is in lexicographical order, your answer could be in any order you want. 
public class Solution {

    static Map<Character, String> keyMappings = new HashMap<>();

    static {
        keyMappings.put('2', "abc");
        keyMappings.put('3', "def");
        keyMappings.put('4', "ghi");
        keyMappings.put('5', "jkl");
        keyMappings.put('6', "mno");
        keyMappings.put('7', "pqrs");
        keyMappings.put('8', "tuv");
        keyMappings.put('9', "wxyz");
    }

    public ArrayList<String> letterCombinations3(String digits) {
        ArrayList<String> ret = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            String mappings = keyMappings.get(digits.charAt(i));
            int preSize = ret.size();
            if (preSize == 0) {
                for (char c : mappings.toCharArray()) {
                    ret.add("" + c);
                }
            }
            for (int j = 0; j < preSize; j++) {
                String preStr = ret.remove(0);
                for (char c : mappings.toCharArray()) {
                    ret.add(preStr + c);
                }
            }

        }
        return ret;
    }

    public ArrayList<String> letterCombinations2(String digits) {
        ArrayList<String> list = new ArrayList<String>();
        doProbe(digits, 0, "", list);
        return list;
    }

    void doProbe(String digits, int index, String current, List<String> list) {
        if (index == digits.length()) {
            list.add(current);
            return;
        }
        for (char c : keyMappings.get(digits.charAt(index)).toCharArray()) {
            doProbe(digits, index + 1, current + c, list);
        }
    }

    public ArrayList<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            ArrayList<String> ret = new ArrayList<String>();
            ret.add("");
            return ret;
        }
        Map<Character, String> keyMappings = new HashMap<Character, String>();
        keyMappings.put('2', "abc");
        keyMappings.put('3', "def");
        keyMappings.put('4', "ghi");
        keyMappings.put('5', "jkl");
        keyMappings.put('6', "mno");
        keyMappings.put('7', "pqrs");
        keyMappings.put('8', "tuv");
        keyMappings.put('9', "wxyz");
        LinkedList<String> rst = new LinkedList<String>();

        if (digits.length() > 0) {
            for (char c : keyMappings.get(digits.charAt(0)).toCharArray())
                rst.add(c + "");

        }

        for (int i = 1; i < digits.length(); i++) {
            String newValues = keyMappings.get(digits.charAt(i));
            int size = rst.size();
            for (int j = 0; j < size; j++) {
                String prev = rst.remove(0);
                for (char c : newValues.toCharArray()) {
                    rst.add(prev + c);
                }
            }
        }

        return new ArrayList<String>(rst);
    }

    public static void main(String[] args) {
        new Solution().letterCombinations3("23").forEach(System.out::println);
    }

}
