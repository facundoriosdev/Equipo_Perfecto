package model;

public class ResolverRunnable implements Runnable {

    private BackTracking algoritmo;

    public ResolverRunnable(BackTracking algoritmo) {
        this.algoritmo = algoritmo;
    }

    @Override
    public void run() {
        algoritmo.resolver();
    }
}