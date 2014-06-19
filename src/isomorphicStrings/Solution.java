package isomorphicStrings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Given two (dictionary) words as Strings, determine if they are isomorphic.
//
//Two words are called isomorphic if the letters in one word can be remapped to get the second word.
//
//Remapping a letter means replacing all occurrences of it with another letter while the ordering of the letters remains unchanged.
//
//No two letters may map to the same letter, but a letter may map to itself.
//
// Example:
//
//given "foo", "app"; returns true we can map f -> a and o->p
//given "bar", "foo"; returns false we can't map both 'a' and 'r' to 'o'
//given "ab", "ca"; returns true we can map 'a' -> 'c' and 'b' -> 'a'
public class Solution {
	boolean isIsomorphic(String s1, String s2) {
		Map<Character, Character> map = new HashMap<Character, Character>();
		Set<Character> values = new HashSet<Character>();
		for (int i = 0; i < s1.length(); i++) {
			char left = s1.charAt(i);
			char right = s2.charAt(i);
			// the key was mapped to a different value
			if (map.containsKey(left) && map.get(left) != right
			// another key has been mapped to this value
					|| !map.containsKey(left) && values.contains(right))
				return false;
			map.put(left, right);
			values.add(right);
		}
		return true;
	}

	public static void main(String[] args) {
		String s1 = "ab";
		String s2 = "ba";
		System.out.println(new Solution().isIsomorphic(s1, s2));
	}
}
