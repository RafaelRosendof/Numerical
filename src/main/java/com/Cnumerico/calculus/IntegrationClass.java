package com.Cnumerico.calculus;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.Function;
import java.util.stream.IntStream;

public class IntegrationClass{
//por enquanto só essas duas 
    public BigDecimal trapezoidalRule(Function<BigDecimal, BigDecimal> f , BigDecimal a , BigDecimal b , int n , BigDecimal tol , MathContext mc){
        BigDecimal prevRes = BigDecimal.ZERO;
        BigDecimal currentRes = calculateTrapz(f , a , b , n , mc);

        while(currentRes.subtract(prevRes).abs().compareTo(tol) > 0){
            n *= 2;
            prevRes = currentRes;
            currentRes = calculateTrapz(f , a , b , n , mc);
        }
        return currentRes;
    }

    public BigDecimal calculateTrapz(Function<BigDecimal , BigDecimal> f , BigDecimal a , BigDecimal b , int n , MathContext mc){
        BigDecimal h = b.subtract(a).divide(BigDecimal.valueOf(n) , mc);

        BigDecimal sum = IntStream.range(1,n).parallel().mapToObj(i -> f.apply(a.add(BigDecimal.valueOf(i).multiply(h,mc))))
        .reduce(BigDecimal.ZERO , BigDecimal::add);

        sum = sum.add(f.apply(a).add(f.apply(b)).divide(BigDecimal.valueOf(2) , mc));

        return sum.multiply(h , mc);

        
    }


    /*
     * simpson 1/3 and 3/8
     * roomberg integration
     */
}