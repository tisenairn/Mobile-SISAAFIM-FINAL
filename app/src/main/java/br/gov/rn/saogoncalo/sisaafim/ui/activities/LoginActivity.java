package br.gov.rn.saogoncalo.sisaafim.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.gov.rn.saogoncalo.sisaafim.R;

public class LoginActivity extends AppCompatActivity {
    private android.support.design.widget.TextInputEditText mEtMatricula;
    private android.support.design.widget.TextInputEditText mEtSenha;
    private Button mBtEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarComponentes();
        definirEventos();
    }

    private void definirEventos() {
        mBtEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        LoginActivity.this,
                        ListaDePendenciaActivity.class
                ));
                finish();
            }
        });
    }

    private void inicializarComponentes() {
        mEtMatricula = findViewById(R.id.ac_login_tv_matricula);
        mEtSenha = findViewById(R.id.ac_login_tv_senha);
        mBtEntrar = findViewById(R.id.ac_login_bt_entrar);
    }
}
