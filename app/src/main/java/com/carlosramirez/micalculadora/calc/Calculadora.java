package com.carlosramirez.micalculadora.calc;

public class Calculadora implements ICalculadora {

    private IControl control;
    private IDisplay display;
    private IProcesamiento proceso;

    @Override
    public void setControl(IControl control) {
        this.control = control;
    }

    @Override
    public void setDisplay(IDisplay display) {
        this.display = display;
    }

    @Override
    public void setProcesamiento(IProcesamiento proceso) {
        this.proceso = proceso;
    }

    @Override
    public String calcular(int x, int y, Operacion op) {
        float resultado = 0f;

        switch (op) {
            case SUM:
                resultado = proceso.suma(x, y);
                break;
            case RES:
                resultado = proceso.resta(x, y);
                break;
            case MUL:
                resultado = proceso.multi(x, y);
                break;
            case DIV:
                resultado = proceso.div(x, y);
                break;
        }

        // Manejo de errores: culadoraError
        if (Float.isNaN(resultado)) {
            // Por ejemplo, división entre 0
            return display.muestraError(CalculadoraError.DIV_ZERO);
        }

        return display.muestraResultado(resultado);
    }
}

