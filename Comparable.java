interface Comparable {
    public static int compareTo(LargeDepositor ld1, LargeDepositor ld2) {
        if ((ld1.getSavings() - ld1.getTaxedIncome()) < (ld2.getSavings() - ld2.getTaxedIncome())) return -1;
        else return 1;
    }
}
