class Solution {
    public int bitwiseComplement(int n) {
        if (n == 0) return 1;
        int mask = Integer.highestOneBit(n) * 2 - 1;
        return n ^ mask;
    }
}
