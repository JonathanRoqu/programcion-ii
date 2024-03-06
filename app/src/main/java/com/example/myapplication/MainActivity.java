package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tempVal;
    private RadioGroup opt;
    private Button btn;

    private static final int OPT_SUMA = R.id.optSuma;
    private static final int OPT_RESTA = R.id.optResta;
    private static final int OPT_MULTIPLICACION = R.id.optMultiplicacion;
    private static final int OPT_DIVISION = R.id.optDivision;
    private static final int OPT_EXPONENCIACION = R.id.optExponenciacion;
    private static final int OPT_PORCENTAJE = R.id.optPorcentaje;
    private static final int OPT_FACTORIAL = R.id.optFactorial;
    private static final int OPT_RAIZ = R.id.optRaiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnCalcular);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateResult();
            }
        });
    }

    private void calculateResult() {
        try {
            opt = findViewById(R.id.optCalculadora);

            tempVal = findViewById(R.id.txtnum1);
            double num1 = Double.parseDouble(tempVal.getText().toString());

            tempVal = findViewById(R.id.txtnum2);
            double num2;

            // Verificar si num2 está vacío en caso de OPT_FACTORIAL
            if (opt.getCheckedRadioButtonId() == OPT_FACTORIAL && tempVal.getText().toString().isEmpty()) {
                num2 = num1;
            } else {
                num2 = Double.parseDouble(tempVal.getText().toString());
            }

            double resp = 0;

            if (opt.getCheckedRadioButtonId() == OPT_SUMA) {
                resp = num1 + num2;
            } else if (opt.getCheckedRadioButtonId() == OPT_RESTA) {
                resp = num1 - num2;
            } else if (opt.getCheckedRadioButtonId() == OPT_MULTIPLICACION) {
                resp = num1 * num2;
            } else if (opt.getCheckedRadioButtonId() == OPT_DIVISION) {
                if (num2 != 0) {
                    resp = num1 / num2;
                } else {
                    Toast.makeText(MainActivity.this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                    return;
                }
            } else if (opt.getCheckedRadioButtonId() == OPT_EXPONENCIACION) {
                resp = Math.pow(num1, num2);
            } else if (opt.getCheckedRadioButtonId() == OPT_PORCENTAJE) {
                resp = (num2 != 0) ? (num1 * num2) / 100 : 0;
            } else if (opt.getCheckedRadioButtonId() == OPT_FACTORIAL) {
                resp = factorial(num1);
            } else if (opt.getCheckedRadioButtonId() == OPT_RAIZ) {
                resp = Math.sqrt(num1);
            }

            tempVal = findViewById(R.id.lblrespuesta);
            tempVal.setText("Resultado: " + resp);
        } catch (Exception e) {
            handleException(e);
        }
    }

    private double factorial(double n) {
        return (n == 0 || n == 1) ? 1 : n * factorial(n - 1);
    }

    private void handleException(Exception e) {
        tempVal = findViewById(R.id.lblrespuesta);
        tempVal.setText("Error: " + e.getMessage());
    }
}
