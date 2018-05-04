package CHelper.src.algo;

public class math {
    public static int gcd(int p, int q) {
        if (q == 0)
            return p;
        int r = p % q;
        return gcd(q, r);
    }

    public static long gcd(long p, long q) {
        if (q == 0)
            return p;
        long r = p % q;
        return gcd(q, r);
    }

    public static long pow(int base, int pow){
        if(pow==0)
            return 1;

        if(pow<0){
            base = 1/base;
            pow = -pow;
        }
        long ret = pow(base,pow/2);
        ret = ret * ret;
        if(pow%2!=0)
            ret = ret * base;
        return ret;
    }

    public static int nextPowerOf2(int n){
        n--;
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        n++;

        return n;
    }

    public static long factorial(int k) {
        long f = 1;
        for (int i=1; i<=k; i++)// in 1..k - 1
            f *= i;
        return f;
    }

    private static void SieveOfEratosthenes(int n, boolean prime[],  boolean primesquare[], int a[])
    {
        for (int i=2; i<=n; i++)
            prime[i] = true;

        for (int i = 0; i < ((n * n) + 1); i++)
            primesquare[i] = false;

        prime[1] = false;

        for (int p = 2; p * p <= n; p++)
        {
            if (prime[p] == true)
            {
                for (int i = p * 2; i <= n; i += p)
                    prime[i] = false;
            }
        }

        int j = 0;
        for (int p = 2; p <= n; p++)
        {
            if (prime[p])
            {
                a[j] = p;
                primesquare[p*p] = true;
                j++;
            }
        }
    }

    public static int countDivisors(int n)
    {
        if (n == 1)
            return 1;

        boolean prime[] = new boolean[n + 1];
        boolean primesquare[] = new boolean[(n * n) + 1];
        int a[] = new int[n];
        SieveOfEratosthenes(n, prime, primesquare, a);

        int ans = 1;

        for (int i = 0; ; i++)
        {
            if (a[i] * a[i] * a[i] > n)
                break;

            int cnt = 1;
            while (n % a[i] == 0)
            {
                n = n / a[i];
                cnt = cnt + 1;
            }
            ans = ans * cnt;
        }

        if (prime[n])
            ans = ans * 2;
        else if (primesquare[n])
            ans = ans * 3;
        else if (n != 1)
            ans = ans * 4;

        return ans;
    }

    //You are given two integers start and end. Count how many numbers in the interval [A, B] have an odd number of divisors.
    public static int oddDivisorCountInRange(int start, int end){
        //return (int)Math.sqrt(end) - (int)Math.sqrt(start-1);
        int count = 0;
        for(int i=start; i<=end; i++)
            if(isPerfectSquare(i))
                count++;
        return count;
    }

    public static int perfectSquresInRange(int start, int end){
        return (int)Math.sqrt(end) - (int)Math.sqrt(start-1);
    }

    public static boolean isPerfectSquare(int n)
    {
        int sum = 0;
        for(int i=1; sum < n; i += 2)
        {
            sum += i;
            if (sum == n)
                return true;
        }
        return false;
    }
}
