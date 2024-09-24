package com.Cnumerico.calculus;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.Function;
import java.util.stream.IntStream;

public class DerivatesClass {
    // just some random methods for calculate derivates
    /*
     * Forward difference method
     * Central difference method
     * Richardson extrapolation TODO 
     * 
     * The methods are for multiple points and for only one point
     * in today have 3 methods 09/09/2024 
     */ 
    //for multiple points 
    public BigDecimal[] forwardMethod(Function<BigDecimal , BigDecimal> f , BigDecimal [] x  , BigDecimal h , MathContext mc){
        return IntStream.range(0, x.length)
                .parallel()
                .mapToObj(i -> f.apply(x[i].add(h, mc))
                        .subtract(f.apply(x[i]), mc)
                        .divide(h, mc))
                .toArray(BigDecimal[]::new);

    }

    //for multiple points
    public BigDecimal[] backwardMethod(Function<BigDecimal , BigDecimal> f , BigDecimal [] x , BigDecimal h , MathContext mc){
        return IntStream.range(0, x.length)
                .parallel()
                .mapToObj(i -> f.apply(x[i])
                        .subtract(f.apply(x[i].subtract(h, mc)), mc)
                        .divide(h, mc))
                .toArray(BigDecimal[]::new);
    }

    public BigDecimal OnlyOneForward(Function<BigDecimal , BigDecimal> f , BigDecimal x , BigDecimal h , MathContext mc){
        return f.apply(x).add(h, mc)
                .subtract(f.apply(x), mc)
                .divide(h, mc);
    }

    public BigDecimal OnlyOneBackward(Function<BigDecimal , BigDecimal> f , BigDecimal x , BigDecimal h , MathContext mc){
        return f.apply(x)
                .subtract(f.apply(x).subtract(h, mc), mc)
                .divide(h, mc);
    }

    public BigDecimal[] centralDiff(Function<BigDecimal , BigDecimal> f , BigDecimal[] x , BigDecimal h , MathContext mc){
        return IntStream.range(0, x.length)
                .parallel()
                .mapToObj(i -> f.apply(x[i].add(h,mc))
                .subtract(f.apply(x[i].subtract(h,mc)),mc)
                .divide(h.multiply(BigDecimal.valueOf(2),mc),mc))
                .toArray(BigDecimal[]::new);
    }
}

/*
 * forwardMethod
 * backwardMethod
 * OnlyOneForward
 * OnlyOneBackward
 * centralDiff
 */


    /* 

    public BigDecimal[] richardDerivate(Function<BigDecimal , BigDecimal> f , BigDecimal[] x , BigDecimal h , MathContext mc){

    }
}
    */

    /* 
    public BigDecimal calculateForward(Function<BigDecimal , BigDecimal>f , BigDecimal x , MathContext mc , BigDecimal h , BigDecimal error){
        BigDecimal prevRes = BigDecimal.ZERO;
        BigDecimal currentRes = forwardMethod(f, x, h, mc);

        while(currentRes.subtract(prevRes).abs().compareTo(error) > 0){
            h = h.divide(BigDecimal.valueOf(2), mc);
            prevRes = currentRes;
            currentRes = f.apply(x).add(h, mc)
                    .subtract(f.apply(x), mc)
                    .divide(h, mc);
        }
        return currentRes;
    }
    */


    /*
     * some kind of diferenciation method with erro
     * 
     *     public BigDecimal[] forwardMethodWithError(Function<BigDecimal, BigDecimal> f, BigDecimal[] x, BigDecimal h, MathContext mc) {
        BigDecimal[] derivatives = new BigDecimal[x.length];
        BigDecimal[] errors = new BigDecimal[x.length];
        
        // First approximation with step size h
        BigDecimal[] firstApprox = IntStream.range(0, x.length)
                .parallel()
                .mapToObj(i -> f.apply(x[i].add(h, mc))
                        .subtract(f.apply(x[i]), mc)
                        .divide(h, mc))
                .toArray(BigDecimal[]::new);

        // Second approximation with step size h/2
        BigDecimal halfH = h.divide(BigDecimal.valueOf(2), mc);
        BigDecimal[] secondApprox = IntStream.range(0, x.length)
                .parallel()
                .mapToObj(i -> f.apply(x[i].add(halfH, mc))
                        .subtract(f.apply(x[i]), mc)
                        .divide(halfH, mc))
                .toArray(BigDecimal[]::new);

        // Estimate derivative and error
        for (int i = 0; i < x.length; i++) {
            derivatives[i] = secondApprox[i];  // Better approximation with h/2
            errors[i] = secondApprox[i].subtract(firstApprox[i], mc).abs();  // Error estimate
        }

        // Print results
        for (int i = 0; i < x.length; i++) {
            System.out.println("f'(" + x[i] + ") ≈ " + derivatives[i].toPlainString() + " (Error ≈ " + errors[i].toPlainString() + ")");
        }

        return derivatives;
    }

     */