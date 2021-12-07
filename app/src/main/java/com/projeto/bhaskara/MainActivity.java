package com.projeto.bhaskara;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.Math;
import java.text.DecimalFormat;
import java.util.Objects;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Obtem os campos EditText, ou seja as caixas de texto:
        EditText varA = findViewById(R.id.ET_VarA);
        varA.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_CLASS_NUMBER); //Define para onlyInt
        EditText varB = findViewById(R.id.ET_VarB);
        varB.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_CLASS_NUMBER); //Define para onlyInt
        EditText varC = findViewById(R.id.ET_VarC);
        varC.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_CLASS_NUMBER); //Define para onlyInt

        // Obtem os campos dos botões:
        Button BtDelta = findViewById(R.id.Bt_CalcDelta);
        Button BtRaiz = findViewById(R.id.Bt_raizes);

        // obtem o campo textview:
        TextView resposta = findViewById(R.id.txt_resposta);
        resposta.setText("");

        // Evento mudança de texto (A):
        varA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                resposta.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                resposta.setText("");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                resposta.setText("");
            }
        });
        // Evento mudança de texto (A):
        varB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                resposta.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                resposta.setText("");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                resposta.setText("");
            }
        });
        // Evento mudança de texto (A):
        varB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                resposta.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                resposta.setText("");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                resposta.setText("");
            }
        });

        // Evento de click calculo do delta, com lambda:
        BtDelta.setOnClickListener(view -> ExibeDelta(varA, varB, varC));

        // Evento de click calculo do delta, com lambda:
        BtRaiz.setOnClickListener(view -> ExibeRaizes(varA, varB, varC));
    }

    private void ExibeRaizes(EditText StrA, EditText StrB, EditText StrC) {
        // obtem o campo textview:
        TextView resp = findViewById(R.id.txt_resposta);

        // Minimiza o teclado:
        MinKeyBoard();


        if (VerificaCampos(StrA, StrB, StrC)){
            resp.setText("Algum campo é inválido!\n Lembre-se a equação deve ser completa!");
        }else{
            // Converte para inteiro:
            int A = Integer.parseInt(StrA.getText().toString());
            int B = Integer.parseInt(StrB.getText().toString());
            int C = Integer.parseInt(StrC.getText().toString());

            // Calculos Delta:
            double Delta = (Math.pow(B, 2)) - (4*A*C);

            // Calculos raizes 1:
            double x1pt1 = (B*(-1) + Math.sqrt(Delta));
            double x2pt1 = (B*(-1) - Math.sqrt(Delta));
            double xpt2 = 2*A;
            double x1 = x1pt1 / xpt2;
            double x2 = x2pt1 / xpt2;
            double xD0pt1 = B*(-1);
            double xD0 = xD0pt1 / xpt2;

            // Variavel do texto com a resposta:
            String Resposta;

            if (Delta < 0){
                Resposta = "Não há raizes reais.";
            }else if(Delta > 0){
                Resposta = "X = (-B ± √Δ) / 2*A\n";
                Resposta += "X = (- " + B + " ± √" + (int) Delta + ") / 2*" + A + "\n";
                Resposta += "X1 = (" + arredondarValor(x1pt1) + ") / " + arredondarValor(xpt2) + "\n";
                Resposta += "X2 = (" + arredondarValor(x2pt1) + ") / " + arredondarValor(xpt2) + "\n";
                Resposta += "X1 = " + arredondarValor(x1) + "\n";
                Resposta += "X2 = " + arredondarValor(x2);
            }else{
                Resposta = "X = (-B) / 2*A\n";
                Resposta += "X = (- " + B + ") / 2*" + A + "\n";
                Resposta += "X = (" + xD0pt1 + ") / " + (int) xpt2 + "\n";
                Resposta += "X = (" + xD0pt1 + ") / " + (int) xpt2 + "\n";
                Resposta += "X = " + arredondarValor(xD0);
            }

            // Exibe a resposta na tela:
            resp.setText(Resposta);
        }
    }

    private void MinKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(MainActivity.INPUT_METHOD_SERVICE);
        if(imm.isActive())
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    private void ExibeDelta(EditText StrA, EditText StrB, EditText StrC) {
        // Exibe a resposta na tela:
        TextView resp = findViewById(R.id.txt_resposta);

        // Minimiza o teclado:
        MinKeyBoard();

        if (VerificaCampos(StrA, StrB, StrC)){
            resp.setText("Algum campo é inválido!\n Lembre-se a equação deve ser completa!");
        }else{
            // Converte para inteiro:
            int A = Integer.parseInt(StrA.getText().toString());
            int B = Integer.parseInt(StrB.getText().toString());
            int C = Integer.parseInt(StrC.getText().toString());

            // Calculos Delta:
            double DeltaPt1 = Math.pow(B, 2);
            double DeltaPt2 = 4*A*C;
            double Delta = DeltaPt1 - DeltaPt2;

            // Cria e começa varieval do texto com a resposta:
            String Resposta = "Δ = B² - 4*A*C\n";
            Resposta += "Δ = " + B + "² - 4*" + A + "*" + C + "\n";
            Resposta += "Δ = " + (int) DeltaPt1 + " - " + (int) DeltaPt2 + "\n";
            Resposta += "Δ = " + (int) Delta + "\n";

            if (Delta > 0){
                Resposta += "Há duas raizes reais.";
            }else if (Delta < 0){
                Resposta += "Não há raizes reais.";
            }else{
                Resposta += "Há apenas uma raiz real.";
            }
            resp.setText(Resposta);
        }
    }

    private boolean VerificaCampos(EditText strA, EditText strB, EditText strC) {
        // Verifica o A:
        if (strA.getText().toString().replace(" ", "").equals("")){
            return true;
        }else{
            int A = Integer.parseInt(strA.getText().toString());
            if (A == 0){
                return true;
            }else{
                // Verifica o B:
                if (strB.getText().toString().replace(" ", "").equals("")){
                    return true;
                }else{
                    int B = Integer.parseInt(strB.getText().toString());
                    if (B == 0){
                        return true;
                    }else{
                        // Verifica o C:
                        if (strC.getText().toString().replace(" ", "").equals("")){
                            return true;
                        }else{
                            int C = Integer.parseInt(strC.getText().toString());
                            return C == 0;
                        }
                    }
                }
            }
        }
    }

    private double arredondarValor(Double vl){
        return Double.parseDouble(new DecimalFormat("#,##0.00").format(vl));
    }
}