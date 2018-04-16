package CHelper.src.algo;

import java.util.HashMap;

public class string {
    public static int LongestSubstringWithoutRepeatingCharacters(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int maxLen = 0;
        int len = 1;
        int checkIndex = 0;
        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            len++;
            for (int j = checkIndex; j < i; j++) {
                if (chars[i] == chars[j]) {
                    if (maxLen < len - 1) {
                        maxLen = len - 1;
                    }
                    checkIndex = j + 1;
                    len = i - j;
                    break;
                }
            }
        }
        return Math.max(maxLen, len);
    }

    public static HashMap<String, Integer> counter(String[] arr)
    {
        HashMap<String, Integer> counter = new HashMap<>();

        for (String a : arr) {
            if (counter.containsKey(a)) {
                int oldValue = counter.get(a);
                counter.put(a, oldValue + 1);
            } else {
                counter.put(a, 1);
            }
        }
        return counter;
    }

    public static HashMap<Character, Integer> counter(char[] arr)
    {
        HashMap<Character, Integer> counter = new HashMap<>();

        for (char a : arr) {
            if (counter.containsKey(a)) {
                int oldValue = counter.get(a);
                counter.put(a, oldValue + 1);
            } else {
                counter.put(a, 1);
            }
        }
        return counter;
    }
}