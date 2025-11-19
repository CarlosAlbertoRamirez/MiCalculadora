package com.carlosramirez.micalculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.carlosramirez.micalculadora.calc.Calculadora;
import com.carlosramirez.micalculadora.calc.CalculadoraError;
import com.carlosramirez.micalculadora.calc.ICalculadora;
import com.carlosramirez.micalculadora.calc.IControl;
import com.carlosramirez.micalculadora.calc.IDisplay;
import com.carlosramirez.micalculadora.calc.IProcesamiento;
import com.carlosramirez.micalculadora.calc.Operacion;
import com.carlosramirez.micalculadora.calc.Procesamiento;

public class MainActivity extends AppCompatActivity implements IDisplay, IControl {

    private EditText inputX, inputY;
    private Spinner spinnerOperacion;
    private TextView tvResultado;

    private ICalculadora calc;
    private IProcesamiento proceso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias UI
        inputX = findViewById(R.id.inputX);
        inputY = findViewById(R.id.inputY);
        spinnerOperacion = findViewById(R.id.spinnerOperacion);
        tvResultado = findViewById(R.id.tvResultado);
        Button btnCalcular = findViewById(R.id.btnCalcular);

        // Modelo spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                new String[]{"Suma", "Resta", "Multiplicación", "División"}
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOperacion.setAdapter(adapter);

        // Crear instancias
        proceso = new Procesamiento();
        calc = new Calculadora();
        calc.setControl(this);
        calc.setDisplay(this);
        calc.setProcesamiento(proceso);

        // Botón
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutar();
            }
        });
    }

    // ======================================================
    // IDisplay
    // ======================================================

    @Override
    public String muestraResultado(float res) {
        String texto = "Resultado: " + res;
        tvResultado.setText(texto);
        return texto;
    }

    @Override
    public String muestraError(CalculadoraError error) {
        String msg;
        switch (error) {
            case DIV_ZERO:
                msg = "Error: División entre 0";
                break;
            default:
                msg = "Error desconocido";
        }
        tvResultado.setText(msg);
        return msg;
    }

    // ======================================================
    // IControl
    // ======================================================

    @Override
    public void introduceValorX(int x) { }

    @Override
    public void introduceValorY(int y) { }

    @Override
    public void introduceOperacion(Operacion op) { }

    @Override
    public float igual() { return 0; }

    // ======================================================
    // Lógica principal
    // ======================================================

    public void ejecutar() {
        String textoX = inputX.getText().toString();
        String textoY = inputY.getText().toString();

        if (textoX.isEmpty() || textoY.isEmpty()) {
            tvResultado.setText("Introduce ambos valores");
            return;
        }

        int x = Integer.parseInt(textoX);
        int y = Integer.parseInt(textoY);

        Operacion op;
        switch (spinnerOperacion.getSelectedItemPosition()) {
            case 0:
                op = Operacion.SUM;
                break;
            case 1:
                op = Operacion.RES;
                break;
            case 2:
                op = Operacion.MUL;
                break;
            default:
                op = Operacion.DIV;
        }

        String texto = calc.calcular(x, y, op);
        tvResultado.setText(texto);
    }
}




