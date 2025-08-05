/*class Solution {
    public int integerReplacement(int n) {
        if(n == Integer.MAX_VALUE)
        {
            return 32;
        }
        return func(n);
    }

    private int func(int n)
    {
        if(n <= 1)
        {
            return 0;
        }
        int v = getPowerOfTwo(n);
        if(v != -1)
        {
            return v;
        }
        int even = Integer.MAX_VALUE;
        int odd1 = Integer.MAX_VALUE;
        int odd2 = Integer.MAX_VALUE;

        if(n%2 == 0)
        {
            even = 1+func(n/2);
        }
        else
        {
            odd1 = 1+func(n-1);
            odd2 = 1+func(n+1);
        }

        return Math.min(odd1,Math.min(odd2,even));
    }

    public int getPowerOfTwo(int n) {
    if (n > 0 && (n & (n - 1)) == 0) {
        return Integer.numberOfTrailingZeros(n);  
    }
    return -1;
}

}*/

class Solution {
    public int integerReplacement(int n) {
        if (n <= 1) {
            return 0;
        }
        long num = n; // use long to avoid overflow
        int count = 0;
        while (num > 1) {
            if (num % 2 != 0) {
                if (num == 3 || num % 4 == 1) {
                    num -= 1;
                } else {
                    num += 1;
                }
            } else {
                num /= 2;
            }
            count++;
        }
        return count;
    }
}
