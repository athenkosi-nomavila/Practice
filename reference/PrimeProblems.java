import java.util.ArrayList;
import java.util.List;

public class PrimeProblems {

    // EASY: Check if a number is prime - O(sqrt n)
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n % 2 == 0) return n == 2;
        for (int i = 3; (long) i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // EASY: Print/collect all primes up to n (brute force using isPrime)
    public static List<Integer> primesUpTo(int n) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) primes.add(i);
        }
        return primes;
    }

    // MEDIUM: Count primes less than n (Sieve of Eratosthenes) - O(n log log n)
    public static int countPrimes(int n) {
        if (n < 3) return 0;
        boolean[] composite = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!composite[i]) {
                count++;
                for (long j = (long) i * i; j < n; j += i) composite[(int) j] = true;
            }
        }
        return count;
    }

    // MEDIUM: Prime factorization (e.g. 84 -> [2, 2, 3, 7])
    public static List<Integer> primeFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 2; (long) i * i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1) factors.add(n);
        return factors;
    }

    // MEDIUM: Find the nth prime (1-indexed: nthPrime(1) = 2)
    public static int nthPrime(int n) {
        int count = 0, candidate = 1;
        while (count < n) {
            candidate++;
            if (isPrime(candidate)) count++;
        }
        return candidate;
    }

    public static void run() {
        IO.println("===== PRIMES =====");
        IO.println("isPrime(29) = " + isPrime(29) + ", isPrime(30) = " + isPrime(30));
        IO.println("primesUpTo(30) = " + primesUpTo(30));
        IO.println("countPrimes(100) = " + countPrimes(100));
        IO.println("primeFactors(84) = " + primeFactors(84));
        IO.println("nthPrime(10) = " + nthPrime(10));
        IO.println();
    }
}
