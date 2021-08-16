package br.com.casadocodigo.boaviagem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaGastoActivity extends ListActivity implements AdapterView.OnItemClickListener {

    private List<Map<String, Object>> gastos;
    public String dataAnterior = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_lista_gasto);
        //primeira versao
        //setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listarGastos()));
        //ListView listView = getListView();
        //listView.setOnItemClickListener(this);
        String[] de = {"data", "descricao", "valor", "categoria"};
        int[] para = {R.id.txtDataGasto, R.id.txtDescricaoGasto, R.id.txtValorGasto, R.id.Categoria};
        SimpleAdapter adapter = new SimpleAdapter(this, listarGastos(), R.layout.activity_lista_gasto, de, para);
        adapter.setViewBinder(new GastoViewBinder());
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

        //private List<String> listarGastos(){
        //return Arrays.asList("Sanduíche R$ 19,90", "Táxi Aeroporto - Hotel R$ 34,00", "Revista R$ 12,00");
        //}
        //registro do menu de contexto
        registerForContextMenu(getListView());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.gasto_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.remover){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            gastos.remove(info.position);
            getListView().invalidateViews();
            dataAnterior = "";
            //remover base de dados
            return true;
        }
        return super.onContextItemSelected(item);
    }

    private List<Map<String, Object>> listarGastos() {
        gastos = new ArrayList<Map<String, Object>>();
        Map<String, Object> item = new HashMap<String, Object>();
        item.put("data", "04/02/2012");
        item.put("descricao", "Diária Hotel");
        item.put("valor", "R$ 260,00");
        item.put("categoria", R.color.categoria_hospedagem);

        item = new HashMap<String, Object>();
        item.put("data", "04/02/2012");
        item.put("descricao", "Almoço");
        item.put("valor", "R$ 50,00");
        item.put("categoria", R.color.categoria_alimentacao);
        gastos.add(item);

        item = new HashMap<String, Object>();
        item.put("data", "04/02/2012");
        item.put("descricao", "Uber");
        item.put("valor", "R$ 20,00");
        item.put("categoria", R.color.categoria_transporte);
        gastos.add(item);

        item = new HashMap<String, Object>();
        item.put("data", "05/02/2012");
        item.put("descricao", "Diária Hotel");
        item.put("valor", "R$ 260,00");
        item.put("categoria", R.color.categoria_hospedagem);
        gastos.add(item);

        item = new HashMap<String, Object>();
        item.put("data", "05/02/2012");
        item.put("descricao", "Almoço");
        item.put("valor", "R$ 50,00");
        item.put("categoria", R.color.categoria_alimentacao);
        gastos.add(item);

        item = new HashMap<String, Object>();
        item.put("data", "06/02/2012");
        item.put("descricao", "Diária Hotel");
        item.put("valor", "R$ 260,00");
        item.put("categoria", R.color.categoria_hospedagem);
        gastos.add(item);

        return gastos;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TextView textView = (TextView) view;
        //String msg = "Gasto selecionado: "+ textView.getText();
        Map<String, Object> map = gastos.get(position);
        String desc = (String) map.get("descricao");
        String msg = "Gasto selecionado: "+ desc;

        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(this, ListaGastoActivity.class));

    }
    private class GastoViewBinder implements SimpleAdapter.ViewBinder{

        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            if(view.getId() == R.id.txtDataGasto){
                if(!dataAnterior.equals(data)){
                    TextView textView = (TextView) view;
                    textView.setText(textRepresentation);
                    dataAnterior = textRepresentation;
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.GONE);
                }
                return true;
            }
            if(view.getId() == R.id.Categoria){
                Integer id = (Integer) data;
                view.setBackgroundColor(
                        getResources().getColor(id));
                return true;
            }
            return false;
        }
    }
}