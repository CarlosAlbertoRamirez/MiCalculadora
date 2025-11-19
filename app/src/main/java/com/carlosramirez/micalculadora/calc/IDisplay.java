package com.carlosramirez.micalculadora.calc;

/**
 * Interface que define cómo se muestra el resultado o error.
 */
public interface IDisplay {

    String muestraResultado(float res);

    String muestraError(CalculadoraError error);
}
