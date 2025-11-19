package com.carlosramirez.micalculadora.calc;

public class Procesamiento implements IProcesamiento {

    @Override
    public float suma(float x, float y) {
        return x + y;
    }

    @Override
    public float resta(float x, float y) {
        return x - y;
    }

    @Override
    public float multi(float x, float y) {
        return x * y;
    }

    @Override
    public float div(float x, float y) {
        // Evitar la división entre cero
        if (y == 0) {
            // Devolvemos NaN (Not a Number) para que el control/display
            // puedan detectar el error y mostrarlo con CalculadoraError.DIV_ZERO
            return Float.NaN;
        }
        return x / y;
    }
}
