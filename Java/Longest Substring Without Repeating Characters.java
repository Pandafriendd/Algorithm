/*
the basic idea is, keep a hashmap which stores the characters in string as keys and their positions as values, 
and keep two pointers which define the max substring. move the right pointer to scan through the string , 
and meanwhile update the hashmap. If the character is already in the hashmap, 
then move the left pointer to the right of the same character last found. Note that the two pointers can only move forward.

The variable "j" is used to indicate the index of first character of this substring. 
If the repeated character's index is less than j itself, 
which means the repeated character in the hash map is no longer available this time
*/






   public int lengthOfLongestSubstring(String s) {
        if (s.length()==0) 
			return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max=0;
        for (int i=0, j=0; i<s.length(); ++i){
            if (map.containsKey(s.charAt(i))){
                j = Math.max(j,map.get(s.charAt(i))+1);  //?? if map.get()== null; map.get()+1 ==?
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-j+1);
        }
        return max;
    }

/*
pwwkew
i j k v (i-j+1) m
0 0 p 0 0       1   
1 0 w 1 2       2
2 2 w 2 1       2
3 2 k 3 2       2
4 2 e 4 3       3
5 3 w 5 3       3
*/

