class Solution {
    // time: O(3 ^ n), n is the length of digits string. space: O(n)
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == "" || digits.length() == 0) {
            return res;
        }
        
        String[] digitToLetters = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        StringBuilder sb = new StringBuilder();
        helper(res, sb, 0, digits, digitToLetters);
        return res;
    }
    
    private void helper(List<String> res, StringBuilder sb, int index, String digits, String[] digitToLetters) {
        // base case
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        
        // recursive rule
        int digit = Character.getNumericValue(digits.charAt(index));
        String letters = digitToLetters[digit];
        for (int i = 0; i < letters.length(); i++) {
            sb.append(letters.charAt(i));
            helper(res, sb, index + 1, digits, digitToLetters);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
