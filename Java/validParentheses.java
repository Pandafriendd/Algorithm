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

// Input:"{[]}"
class Solution {
    public boolean isValid(String s) {
        if(s == null || s.length() == 0)
            return true;
        
        Stack<Character> stack = new Stack<>();
        
        if(s.charAt(0) == ')' || s.charAt(0) == '}' || s.charAt(0) == ']')
            return false;
        
        for(int i = 0; i < s.length(); i++) {
            if(stack.isEmpty())
                stack.push(s.charAt(i));
            else {
                if(stack.peek() == '(' && s.charAt(i) != ')' || 
                   stack.peek() == '[' && s.charAt(i) != ']' || 
                   stack.peek() == '{' && s.charAt(i) != '}') {
                    return false;
                }
                else if(stack.peek() == '(' && s.charAt(i) == ')' || 
                        stack.peek() == '[' && s.charAt(i) == ']' || 
                        stack.peek() == '{' && s.charAt(i) == '}') {
                    stack.pop();
                }
                else {
                    stack.push(s.charAt(i));
                }
            }
        }
        
        return stack.isEmpty();
    }
}
