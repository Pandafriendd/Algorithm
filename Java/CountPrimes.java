/*
Count the number of prime numbers less than a non-negative number, n.

Example:
Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */


import java.util.*;
public class CountPrimes {
	// O(n^2) Time Limit Exceeded
	public int countPrimes(int n) {
        if(n < 3) 
            return 0;
        
        int count = 0;
        for(int i = 3; i <= n - 1; i++) {
            if(isPrime(i))
                count++;
        }
        
        return count;
    }
    
    private boolean isPrime(int n) {
        if(n == 2)
            return true;
        
        for(int i = 2; i <= n - 1; i++) {
            if(n % i == 0)
                return false;
        }
        
        return true;
    }
    
    
    // optimized a little, but still time limit exceeded
    public int countPrimes2(int n) {
        if(n < 3) 
            return 0;
        
        int count = 1;  // since n >= 3, count begins with 1
        for(int i = 3; i <= n - 1; i = i + 2) { // !! since even number except 2 cannot be prime
            if(isPrime2(i))
                count++;
        }
        
        return count;
    }
    
    private boolean isPrime2(int n) {
        if(n == 2)
            return true;
        
        for(int i = 2; i <= Math.sqrt(n); i++) { // !!!
            if(n % i == 0)
                return false;
        }
        
        return true;
    }
    
    /*
     * Sieve of Eratosthenes algorithm
     * In the boolean array m, m[n] means the number n 
     * Thus for each time, if m[n] is a prime, we need to delete all the multiple of m[n]. And finally, the remaining numbers are primes.
     */
    public int countPrimes3(int n) {
        // isMultipleOfPrime[i] store whether num i is dividable by a prime num < i
        boolean[] isMultipleOfPrime = new boolean[n]; 
        // count of prime nums so far
        int count = 0;
        for (int i = 2; i < n; i++) {     // start from 2
            if (!isMultipleOfPrime[i]) {  // if i not dividable by a previous num, it's a prime
                count++;                  // count toward total num of primes seen so far
                for (int j=i; j<n; j=j+i) // mark all multiples of i as non-prime
                    isMultipleOfPrime[j] = true;
            }
        }
        return count;
    }   
}
