"""
Cards Permutation
-----------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/cards-permutation
-----------------------------------------

Alice was given the n integers from 1 to n. She wrote all possible permutations in increasing lexicographical order, and wrote each permutation in a new line. For example, for n=3, there are 6 possible permutations:
1. [1,2,3]
2. [1,3,2]
3. [2,1,3]
4. [2,3,1]
5. [3,1,2]
6. [3,2,1]

She then chose one permutation among them as her favorite permutation.

After some time, she forgot some elements of her favorite permutation. Nevertheless, she still tried to write down its elements. She wrote a 0 in every position where she forgot the true value.

She wants to know the sum of the line numbers of the permutations which could possibly be her favorite permutation, i.e., permutations which can be obtained by replacing the 0s. Can you help her out?

Since the sum can be large, find it modulo 10^9+7.

Input Format

The first line contains a single integer n.

The next line contains n space-separated integers a1,a2,a3,....,an denoting Alice's favorite permutation with some positions replaced by 0.

Output Format

Print a single line containing a single integer denoting the sum of the line numbers of the permutations which could possibly be Alice's favorite permutation.

Sample Input 0

4
0 2 3 0

Sample Output 0

23

Explanation 0

The possible permutations are [1,2,3,4] and [4,2,3,1]. The permutation [1,2,3,4] occurs on line 1 and the permutation [4,2,3,1] occurs on line 22. Therefore the sum is 1+22=23.

Sample Input 1

4
4 3 2 1

Sample Output 1

24

Explanation 1

There is no missing number in the permutation. Therefore, the only possible permutation is [4,3,2,1], and it occurs on line 24. Therefore the sum is 24.

"""

(Python Solution)
#!/bin/python3

import os
from bisect import bisect_left, bisect_right

N = 1000000007

def gen_factorial_mod(gen):
    last = 1
    yield last
    if isinstance(gen, int):
        gen = range(1, gen+1)
    for i in gen:
        last = (last * i) % N
        yield last

def solve(a):
    n = len(a)
    if n == 1:
        return 1
    if n == 2:
        return a[0] or (3 - a[1])
    
    
    b = [0] * (n+1)
    free_seen = 0
    if a[0] > 2:
        a_sorted = list(range(1, n+1))
        ff = list(gen_factorial_mod(a_sorted))
        for i, x in enumerate(a, 1):
            if x:
                ind = bisect_left(a_sorted, x)
                b[x] = [i, free_seen, ind] 

                a_sorted[ind:ind+1] = []
            else:
                free_seen += 1
    else:
        a_fixed = []
        ff = list(gen_factorial_mod(n))
        for i, x in enumerate(a, 1):
            if x:
                ind = bisect_left(a_fixed, x)
                b[x] = [i, free_seen, x - 1 - ind]
                a_fixed.insert(ind, x)
            else:
                free_seen += 1
    
    b[0:1] = []
    n_free = free_seen
    num_perms = ff[n_free]
    ff_n1 = ff[n_free - 1]
    ff_n2 = (ff[n_free - 2] * (((n_free - 1) * n_free) // 2)) % N
    total = num_perms

    q = [0] * (n+1)
    total_fixed_contr = smaller_free = 0
    
    for info in b:
        if info:
            pos, l_free, s = info
            q[pos] = n_free - smaller_free
            sr_fixed = s - smaller_free
            r_free = n_free - l_free
            contr = ((sr_fixed * num_perms) % N + (smaller_free * r_free * ff_n1) % N) % N
            total_fixed_contr = (total_fixed_contr + (contr * ff[n - pos]) % N) % N
        else:
            smaller_free += 1
    total = (total + total_fixed_contr) % N
    if not n_free:
        return total

    total_free_contr = cum_bigger_fixed = r_free = 0
    ii = a.index(0)
    a_gen = reversed(a if ii < 10 else a[ii:])
    for x, ipos, iq in zip(a_gen, ff, reversed(q)):
        if iq:
            cum_bigger_fixed = (cum_bigger_fixed + (iq * ff_n1)%N)%N
        elif not x:
            contr = (cum_bigger_fixed + r_free * ff_n2)%N
            total_free_contr = (total_free_contr + (contr * ipos)%N)%N
            r_free += 1
    total = (total + total_free_contr) % N
    return total

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')
    n = int(input())
    a = list(map(int, input().rstrip().split()))
    result = solve(a)
    fptr.write(str(result) + '\n')
    fptr.close()