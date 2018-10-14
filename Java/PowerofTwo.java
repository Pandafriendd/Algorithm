
public class PowerofTwo {
	public boolean isPowerOfTwo(int n) {
        if(n == 1) {
            return true;
        }
        if(n <= 0) {  // corner case
            return false;
        }
        
        while(n != 2) {
            if(n % 2 != 0) {
                return false;
            }
            
            n = n / 2;
        }
        
        return true;
    }
	
	public boolean isPowerOfThree(int n) {
        if(n == 1) {
            return true;
        }
        if(n <= 0) {  // corner case
            return false;
        }
        
        while(n != 3) {
            if(n % 3 != 0) {
                return false;
            }
            
            n = n / 3;
        }
        
        return true;
    }
	
	public boolean isPowerOfFour(int n) {
        if(n == 1) {
            return true;
        }
        if(n <= 0) {  // corner case
            return false;
        }
        
        while(n != 4) {
            if(n % 4 != 0) {
                return false;
            }
            
            n = n / 4;
        }
        
        return true;
    }
}
