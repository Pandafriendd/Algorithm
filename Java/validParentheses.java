class Solution {
    public boolean isValid(String s) {
       if(s == null || s.length() == 0)
           return false;
        Stack<Character> st = new Stack<Character>();
        for(Character c : s.toCharArray()){
            if(!isPar(c))
                continue;
            if("([{".indexOf(c) != -1)
                st.push(c);
            else{
                if(!st.isEmpty() && isMatch(st.peek(), c))
                    st.pop();
                else
                    return false;
            }
        }
        return st.isEmpty();
    }
    
    public boolean isMatch(Character a, Character b){
        return (a == '(' && b ==')' || a == '[' && b ==']' || a == '{' && b =='}');
    }
    
    public boolean isPar(Character c){
        String par = "(){}[]";
        return par.indexOf(c) != -1;
    }
    
}