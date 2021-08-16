package br.com.casadocodigo.boaviagem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.content.DialogInterface.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaViagemActivity extends ListActivity implements OnItemClickListener, OnClickListener {

    private AlertDialog alertDialog;
    private AlertDialog alertDialogConfirmacao;
    private int viagemSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_lista_viagem);
        //primeira versao da lista
        //setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listarViagens()));
        //ListView listView = getListView();
        //listView.setOnItemClickListener(this);
        //segunda versao da lista
        String[] de = {"imagem", "destino", "data", "total"};
        int[] para = {R.id.tipoViagem, R.id.txtDestino, R.id.txtData, R.id.txtValor};
        SimpleAdapter adapter = new SimpleAdapter(this, listarViagens(), R.layout.activity_lista_viagem, de, para);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

        this.alertDialog = criarDialog();
        this.alertDialogConfirmacao = criarDialogConfirmacao();
    }

    //private List<String>listarViagens(){return Arrays.asList("São Paulo", "Bonito", "Maceió");
    private List<Map<String, Object>> viagens;

    private List<Map<String, Object>>listarViagens(){
        viagens = new ArrayList<Map<String, Object>>();

        Map<String, Object> item = new HashMap<String, Object>();
        item.put("imagem", R.drawable.negocios);
        item.put("destino", "São Paulo");
        item.put("data","02/02/2012 a 04/02/2012");
        item.put("total","Gasto total R$ 314,98");
        viagens.add(item);

        item = new HashMap<String, Object>();
        item.put("imagem", R.drawable.lazer);
        item.put("destino", "Maceió");
        item.put("data","14/05/2012 a 22/05/2012");
        item.put("total","Gasto total R$ 25834,67");
        viagens.add(item);

        item = new HashMap<String, Object>();
        item.put("imagem", R.drawable.negocios);
        item.put("destino", "São Paulo");
        item.put("data","14/01/2015 a 17/01/2015");
        item.put("total","Gasto total R$ 1.500,00");
        viagens.add(item);

        return viagens;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TextView textView = (TextView) view;
        //String msg = "Viagem selecionada: "+ textView.getText();

        Map<String, Object> map = viagens.get(position);
        String destino = (String) map.get("destino");
        String msg = "Viagem selecionada: "+ destino;

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ListaGastoActivity.class));

        //pega a posição da viagem selecionada na lista
        this.viagemSelecionada = position;
        alertDialog.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int item) {

        switch (item){
            case 0:
                startActivity(new Intent(this, ViagemActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, GastoActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, ListaGastoActivity.class));
                break;
            case 3:
                criarDialogConfirmacao().show();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                criarDialogConfirmacao().dismiss();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                viagens.remove(this.viagemSelecionada);
                getListView().invalidateViews();
                break;
        }

    }

    private AlertDialog criarDialog(){
        final CharSequence [] items = {
                getString(R.string.editar),
                getString(R.string.novo_gasto),
                getString(R.string.gastos_realizados),
                getString(R.string.remover_gasto)};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.opcoes);
        builder.setItems(items, this);
        return builder.create();
    }

    private AlertDialog criarDialogConfirmacao(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.confirmaexclusao));
        builder.setNegativeButton(R.string.opcao_nao, this);
        builder.setPositiveButton(R.string.opcao_sim, this);
        return builder.create();
    }
}