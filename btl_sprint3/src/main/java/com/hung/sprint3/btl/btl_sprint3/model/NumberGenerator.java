package com.hung.sprint3.btl.btl_sprint3.model;

import java.util.Objects;
import java.util.Random;

public class NumberGenerator {
    private Random rand;
    private String upper;
    private String digits;



    public NumberGenerator(Random rand, String upper, String digits) {
        this.rand = rand;
        this.upper = upper;
        this.digits = digits;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public String getUpper() {
        return upper;
    }

    public void setUpper(String upper) {
        this.upper = upper;
    }

    public String getDigits() {
        return digits;
    }

    public void setDigits(String digits) {
        this.digits = digits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberGenerator that = (NumberGenerator) o;
        return Objects.equals(rand, that.rand) && Objects.equals(upper, that.upper) && Objects.equals(digits, that.digits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rand, upper, digits);
    }

    @Override
    public String toString() {
        return "NumberGenerator{" +
                "rand=" + rand +
                ", upper='" + upper + '\'' +
                ", digits='" + digits + '\'' +
                '}';
    }
}
