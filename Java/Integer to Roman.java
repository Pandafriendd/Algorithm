class Solution {
    public String intToRoman(int num) {
        StringBuilder s = new StringBuilder();
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        for(int i = 0; i < roman.length; i++){
            while(num >= numbers[i]){
                num -= numbers[i];
                s.append(roman[i]);
            }
        }
        return s.toString();
    }
}