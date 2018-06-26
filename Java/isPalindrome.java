
//This is mine
class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        long a = x;   //record x
        long b = 0;   //record the reverse of x
        long i = 0;   //record the unit
        while(a>0){
            i = a % 10;
            a = a / 10;
            b = b * 10 + i;
        }
        if(x == b)
            return true;
        else
            return false;
    }
}

// cool code
public boolean isPalindrome(int x) {
    
    if (x < 0) return false;

    int p = x; 
    int q = 0; 
    
    while (p >= 10){
        q *= 10; 
        q += p%10; 
        p /= 10; 
    }
    
    return q == x / 10 && p == x % 10;   //in case of Int16, check 63556 will finally check if (6553 == 6355 && 6 == 63556%10) so there will have no concerns about the overflow
}