/*
 * Project developed for Financial Engineering Math package
 * Reference: Java methods for Financial engineering @Philip/ Barker
 */
package FinApps;

import BaseStats.Probnorm;

/**
 *
 * @author Muddu
 */
public class Forstartop {

    public Forstartop(double carryrate) {
        crate = carryrate;
    }

    public double getCalle() {
        return callprice;
    }

    public double getPute() {
        return putprice;
    }

    private void setcalle(double call) {
        callprice = call;
    }

    private void setpute(double put) {
        putprice = put;
    }
    private double crate = 0.0;
    private double brate = 0.0;
    private double d1 = 0.0;
    private double d2 = 0.0;
    private double callprice = 0.0;
    private double putprice = 0.0;

    private void dvalues(double sprice, double alpha, double volatility, double time, double tmaturity, double rate) {
        brate = crate < 0.0 ? 0.0 : (brate = crate != rate ? (rate - crate) : rate);
        d1 = ((Math.log(1.0 / alpha) + (brate + (volatility * volatility) * 0.5) * (tmaturity - time)) / (volatility * Math.sqrt(tmaturity - time)));
        d2 = (d1 - (volatility * Math.sqrt(tmaturity - time)));
    }

    public void fstartOp(double sprice, double alpha, double volatility, double time, double tmaturity, double rate) {
        Probnorm p = new Probnorm();
        dvalues(sprice, alpha, volatility, time, tmaturity, rate);
        double probd1 = 0.0;
        double probd2 = 0.0;
        double probdn1 = 0.0;
        double probdn2 = 0.0;
        probd1 = p.ncDisfnc(d1);
        probd2 = p.ncDisfnc(d2);
        probdn1 = p.ncDisfnc(-d1);
        probdn2 = p.ncDisfnc(-d2);
        setcalle(sprice * Math.exp((brate - rate) * time) * ((Math.exp((brate - rate) * (tmaturity - time)) * probd1)
                - (alpha * Math.exp(-rate * (tmaturity - time)) * probd2)));
        setpute(sprice * Math.exp((brate - rate) * time) * ((alpha * Math.exp((-rate) * (tmaturity - time)) * probdn2)
                - (Math.exp((brate - rate) * (tmaturity - time)) * probdn1)));
    }

}
