package br.com.casadocodigo.boaviagem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class DashboardActivity extends Activity {

    //private TextView novoGasto, novaViagem, listaViagens, configuracao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //novoGasto = findViewById(R.id.lblNovoGasto);
        //novaViagem = findViewById(R.id.lblNovaViagem);
        //listaViagens = findViewById(R.id.lblListaViagens);
        //configuracao = findViewById(R.id.lblConfiguracao);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.dashbord_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        //return super.onMenuItemSelected(featureId, item);
        finish();
        return true;
    }

    public void selecionarOpcao(View view){
        switch (view.getId()) {
            case R.id.lblNovoGasto:
                startActivity(new Intent(DashboardActivity.this, GastoActivity.class));
                break;
            case R.id.lblNovaViagem:
                startActivity(new Intent(DashboardActivity.this, ViagemActivity.class));
                break;
            case R.id.lblListaViagens:
                startActivity(new Intent(DashboardActivity.this, ListaViagemActivity.class));
                break;
            case R.id.lblConfiguracao:
                //
        }
    }
}