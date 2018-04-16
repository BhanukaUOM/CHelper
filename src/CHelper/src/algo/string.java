package CHelper.src.algo;

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
}