package decimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class BigDecimalSample {
    public static void main(String[] args) {
        BigDecimal value = new BigDecimal("12314213123123141.00");

        int n = 1000;
        int scaling = 2;
        RoundingMode roundingMode = RoundingMode.HALF_UP;

        List<BigDecimal> portions = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            portions.add(new BigDecimal("" + i));
        }

        BigDecimal total = portions.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        List<BigDecimal> results = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            results.add(
                    value.multiply(portions.get(0)).divide(total, scaling, roundingMode)
            );
        }

        BigDecimal result = results.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(value);
        System.out.println(result);
        System.out.println(result.compareTo(value) == 0);
    }
}