package com.carlosramirez.micalculadora.calc;

public interface ICalculadora {

    void setControl(IControl control);

    void setDisplay(IDisplay display);

    void setProcesamiento(IProcesamiento proceso);

    // IMPORTANTE: añadir este método
    String calcular(int x, int y, Operacion op);
}
