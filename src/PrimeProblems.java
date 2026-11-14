import java.util.ArrayList;
import java.util.List;

public class PrimeProblems {

    /*
     * EASY: Is Prime
     * A prime is a whole number greater than 1 whose only divisors are 1 and itself.
     * Return true if n is prime, otherwise false.
     *
     * Example 1: 29 -> true
     * Example 2: 30 -> false  (divisible by 2, 3, 5...)
     * Example 3: 1  -> false  (1 is not prime)
     * Example 4: 2  -> true   (the only even prime)
     */
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n % 2 == 0) return n == 2;
        for (int i = 3; (long) i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    /*
     * EASY: Primes Up To N
     * Return a list of all prime numbers from 2 up to and including n, in order.
     *
     * Example 1: 10 -> [2, 3, 5, 7]
     * Example 2: 30 -> [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
     * Example 3: 1  -> []
     */
    public static List<Integer> primesUpTo(int n) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) primes.add(i);
        }
        return primes;
    }

    /*
     * MEDIUM: Count Primes
     * Return how many prime numbers are strictly LESS than n.
     * It should stay fast for large n (e.g. 5,000,000).
     *
     * Example 1: 10  -> 4   (2, 3, 5, 7)
     * Example 2: 100 -> 25
     * Example 3: 2   -> 0
     */
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

    /*
     * MEDIUM: Prime Factorization
     * Return the prime factors of n (n >= 2) in ascending order.
     * Repeated factors appear as many times as they divide n,
     * so multiplying the list together gives back n.
     *
     * Example 1: 84  -> [2, 2, 3, 7]   (2 * 2 * 3 * 7 = 84)
     * Example 2: 13  -> [13]           (13 is already prime)
     * Example 3: 100 -> [2, 2, 5, 5]
     */
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

    /*
     * MEDIUM: Nth Prime
     * Return the nth prime number, counting from 1.
     *
     * Example 1: 1  -> 2   (first prime)
     * Example 2: 5  -> 11  (2, 3, 5, 7, 11)
     * Example 3: 10 -> 29
     */
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
