package mekanism.common.util;

import java.util.Random;

import static java.lang.Math.*;

public class StatUtils {

    public static Random rand = new Random();

    public static int inversePoisson(double mean) {
        double r = rand.nextDouble() * exp(mean);
        int m = 0;
        double p = 1;
        double stirlingValue = mean * E;
        double stirlingCoeff = 1 / sqrt(2 * PI);
        while ((p < r) && (m < 3 * ceil(mean))) {
            m++;
            p += stirlingCoeff / sqrt(m) * pow(stirlingValue / m, m);
        }
        return m;
    }
}