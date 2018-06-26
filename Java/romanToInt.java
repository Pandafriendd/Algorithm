class Solution {
    public int romanToInt(String s) {
        int res = 0;
        int len = s.length();
        if(len == 0 || s == null)
            return 0;
        Map<Character, Integer> m = new HashMap<Character, Integer>();
        m.put('I', 1);
        m.put('X', 10);
        m.put('C', 100);
        m.put('M', 1000);
        m.put('V', 5);
        m.put('L', 50);
        m.put('D', 500);
        res = m.get(s.charAt(len - 1));
        for(int i = len - 2; i >= 0; i--){
            if(m.get(s.charAt(i)) < m.get(s.charAt(i + 1)))
                res -= m.get(s.charAt(i));
            else
                res += m.get(s.charAt(i));
        }
        return res;      
    }
}