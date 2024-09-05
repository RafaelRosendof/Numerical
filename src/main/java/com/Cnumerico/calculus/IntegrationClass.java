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

    // a fazer
    public BigDecimal simpson13Rule(Function<BigDecimal , BigDecimal> f , BigDecimal a , BigDecimal b , int n , BigDecimal tol , MathContext mc){
        BigDecimal prevRes = BigDecimal.ZERO;
        BigDecimal currentRes = calculate13(f , a , b , n , mc);

        while(currentRes.subtract(prevRes).abs().compareTo(tol) > 0){
            n *= 2;
            prevRes = currentRes;
            currentRes = calculate13(f , a , b , n , mc);
        }
        return currentRes;
    }

    public BigDecimal calculate13(Function<BigDecimal , BigDecimal>f , BigDecimal a , BigDecimal b , int n , MathContext mc){

        assert n % 2 == 0 : "n must be a multiple of 2";
        BigDecimal h = b.subtract(a).divide(BigDecimal.valueOf(n) , mc);

        BigDecimal sum = IntStream.range(1,n).parallel().mapToObj(i -> { BigDecimal x = a.add(BigDecimal.valueOf(i).multiply(h , mc));
            if(i % 2 == 0){
                return f.apply(x).multiply(BigDecimal.valueOf(2) , mc);
            } else{
                return f.apply(x).multiply(BigDecimal.valueOf(4) , mc);
            }
        }).reduce(BigDecimal.ZERO , BigDecimal::add);

        sum = sum.add(f.apply(a).add(f.apply(b)));

        return sum.multiply(h.divide(BigDecimal.valueOf(3) , mc) , mc);



    }

    public BigDecimal calculateSimp38(Function<BigDecimal , BigDecimal> f , BigDecimal a , BigDecimal b , int n , MathContext mc){
        assert n % 3 == 0 : "n must be a multiple of 3";

        BigDecimal h = b.subtract(a).divide(BigDecimal.valueOf(n) , mc);

        BigDecimal sum = IntStream.range(1,n).parallel().mapToObj(i -> { BigDecimal x = a.add(BigDecimal.valueOf(i).multiply(h , mc));
            if (i % 3 == 0){
                return f.apply(x).multiply(BigDecimal.valueOf(2) , mc);
            } else{
                return f.apply(x).multiply(BigDecimal.valueOf(3) , mc);
            }
            }).reduce(BigDecimal.ZERO, BigDecimal::add);

            sum = sum.add(f.apply(a).add(f.apply(b)));

            return sum.multiply(h.multiply(BigDecimal.valueOf(3),mc).divide(BigDecimal.valueOf(8),mc),mc);

    }

    public BigDecimal simpson38Rule(Function<BigDecimal , BigDecimal>f , BigDecimal a , BigDecimal b , int n , BigDecimal tol , MathContext mc){
        BigDecimal prevRes = BigDecimal.ZERO;
        BigDecimal current = calculateSimp38(f , a ,b , n , mc);

        while(current.subtract(prevRes).abs().compareTo(tol)>0){

            n *= 2;
            prevRes = current;
            current = calculateSimp38(f , a , b , n , mc);
        }
        return current;
    }

    /*
     * simpson 1/3 and 3/8
     * roomberg integration
     */
}