/*
"(newResult - tail) / 10" turns the "result * 10 + tail" back. 
If no overflow exists, "(newResult - tail) / 10" should be equal to "result". 
If overflow happens, they cannot be equal.
*/
public int reverse(int x)
{
    int result = 0;

    while (x != 0)
    {
        int tail = x % 10;
        int newResult = result * 10 + tail;
        if ((newResult - tail) / 10 != result)
        { return 0; }
        result = newResult;
        x = x / 10;
    }

    return result;
	
}
class Solution {
public:
    int reverse(int x) {
        int sign = x < 0 ? -1 : 1;
        x = abs(x);
        int res = 0;
        while (x > 0) {
            if (INT_MAX / 10 < res || (INT_MAX - x % 10) < res * 10) {
                return 0;
            }
            res = res * 10 + x % 10;
            x /= 10;
        }
        
        return sign * res;
    }
};
//this is mine
class Solution {
    public int reverse(int x) {
        int sign = x < 0 ? -1 : 1;
        x = Math.abs(x);
        if (x > 1000000000)
            return 0;
        int y = x; 
        int i = 0;  // unit of every loops
        int res = 0;
        while(y >= 10){
            i = y % 10;
            res = res * 10 + i;
            y = y / 10;
        }
        res = res * 10 + y;  //now y is the unit of result
        return (int) sign * res;
    }
}

//after considering the result might overflow
class Solution {
    public int reverse(int x) {
        int sign = x < 0 ? -1 : 1;
        x = Math.abs(x);
        int y = x; 
        int i = 0;  // unit of every loops
        //int res = 0;
        long res = 0;  //result maybe overflow
        while(y >= 10){
            i = y % 10;
            res = res * 10 + i;
            y = y / 10;
        }
        res = sign * (res * 10 + y);  //now y is the unit of result
        if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE)
            return 0;
        return (int)res;
    }
}
// better code
class Solution{
	pubilc int reverse(int x){
		long res = 0; // res might overflow
		while (x!= 0){
			res = res * 10 + x % 10; // append last digit of x
			x = x / 10;  // remove last digit
		}
		if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE)
			return 0;
		return (int)res;
	}
	
}
