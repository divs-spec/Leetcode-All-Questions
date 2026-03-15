class Fancy {

    long mod = 1000000007;
    List<Long> list;
    long mul = 1;
    long add = 0;

    public Fancy() {
        list = new ArrayList<>();
    }

    public void append(int val) {
        long x = (val - add + mod) % mod;
        x = (x * modInverse(mul)) % mod;
        list.add(x);
    }

    public void addAll(int inc) {
        add = (add + inc) % mod;
    }

    public void multAll(int m) {
        mul = (mul * m) % mod;
        add = (add * m) % mod;
    }

    public int getIndex(int idx) {
        if (idx >= list.size()) return -1;

        long val = list.get(idx);
        return (int)((val * mul + add) % mod);
    }

    private long modInverse(long x) {
        return modPow(x, mod - 2);
    }

    private long modPow(long base, long exp) {
        long result = 1;
        base %= mod;

        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }

        return result;
    }
}
