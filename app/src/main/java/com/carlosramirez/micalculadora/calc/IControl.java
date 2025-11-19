package com.carlosramirez.micalculadora.calc;

/**
 * Interface que define el control de la calculadora (entrada y operación).
 */
public interface IControl {

    void introduceValorX(int x);

    void introduceValorY(int y);

    void introduceOperacion(Operacion op);

    float igual();
}
