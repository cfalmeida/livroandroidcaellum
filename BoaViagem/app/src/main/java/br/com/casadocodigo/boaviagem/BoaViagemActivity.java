package br.com.casadocodigo.boaviagem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BoaViagemActivity extends Activity {

    private EditText usuario;
    private EditText senha;
    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usuario = findViewById(R.id.edtUsuario);
        senha = findViewById(R.id.edtSenha);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuarioInformado = usuario.getText().toString();
                String senhaInformada = senha.getText().toString();

                if(usuarioInformado.equals("")
                        && senhaInformada.equals("")){
                    Intent it = new Intent(BoaViagemActivity.this, DashboardActivity.class);
                    startActivity(it);

                }
                else{
                    String msgErro = getString(R.string.erro_autenticacao);
                    Toast toast = Toast.makeText(BoaViagemActivity.this, msgErro, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}