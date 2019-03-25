class Solution {
    // time: O(3 ^ n * 4 ^ m), n + m is the length of digits string. 
    // n is the number of digits in the input that maps to 3 letters (e.g. 2, 3, 4, 5, 6, 8) and m is the number of digits in the input that maps to 4 letters (e.g. 7, 9)
    // space: O(3 ^ n * 4 ^ m)
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
        int digit = Character.getNumericValue(digits.charAt(index)); // digits.charAt(index) - '0'; would be better
        String letters = digitToLetters[digit];
        for (int i = 0; i < letters.length(); i++) {
            sb.append(letters.charAt(i));
            helper(res, sb, index + 1, digits, digitToLetters);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
    // old old
    private static final String[] LETTERS = {
        "", //0
        "", //1
        "abc", //2
        "def", //3;
        "ghi", //4
        "jkl", //5
        "mno", //6
        "pqrs", //7
        "tuv", //8
        "wxyz", //9
    };
    
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        if(digits == null || digits.length() == 0)
            return res;
        helper(digits, 0, "", res); //backtracking!!!
        return res;
    }
    
    private void helper(String digits, int s, String comb, List<String> res){
        if(s == digits.length()){ // all digits done, return
            res.add(comb);
            return;
        }
        String c = LETTERS[digits.charAt(s) - '0']; //get int index!!!! Since digits is a string!!!
        for(int i = 0; i < c.length(); i++){
            helper(digits, s + 1, comb + c.charAt(i), res); // backtracking!!!
        }
    }
}
