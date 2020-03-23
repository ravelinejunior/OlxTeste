package br.com.tfleet.tests.olxtestes.activies;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.tfleet.tests.olxtestes.R;
import br.com.tfleet.tests.olxtestes.adapters.AdapterYoutubeApi;
import br.com.tfleet.tests.olxtestes.api.YoutubeService;
import br.com.tfleet.tests.olxtestes.helper.YoutubeConfig;
import br.com.tfleet.tests.olxtestes.listener.RecyclerItemClickListener;
import br.com.tfleet.tests.olxtestes.model.Items;
import br.com.tfleet.tests.olxtestes.model.Resultado;
import br.com.tfleet.tests.olxtestes.services.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class YoutubeApiActivity extends AppCompatActivity {
    private MaterialSearchView searchView;
    private Retrofit retrofit;
    private RecyclerView recyclerViewYoutubeApi;

    //listas
    private List<Items> itemsList = new ArrayList<>();
    private AdapterYoutubeApi adapterYoutubeApi = new AdapterYoutubeApi(itemsList, getContext());
    private Resultado resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_api);

        carregareElementos();
        inicializacaoRetrofit();

        //para inicializar todos os videos
        recuperarVideos("Dicionario do programador");

        //metodo de pesquisa //recuperar os videos
        pesquisaGeral();





    }

    private void carregareElementos(){
        Toolbar toolbar = findViewById(R.id.toolbar_YoutubeApi);
        toolbar.setTitle("YouTube Api");
        setSupportActionBar(toolbar);
        //elementos
        recyclerViewYoutubeApi = findViewById(R.id.recyclerView_Youtubevideos_id);
        searchView = findViewById(R.id.pesquisaIcon_Youtube_id);


    }

    public void inicializacaoRetrofit(){
        retrofit = RetrofitConfig.getRetrofit();
    }

    public void pesquisaGeral(){


        //efeitos de clique no botao de pesquisa
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //ativar voiceSearch no codigo
                searchView.setVoiceSearch(true); //or false
            }

            @Override
            public void onSearchViewClosed() {
                recuperarVideos("");
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recuperarVideos(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public Context getContext(){
        return YoutubeApiActivity.this;
    }

    private void recuperarVideos(String pesquisa){

        //criar String com formatação de espaço para digitação na pesquisa
        String q = pesquisa.replaceAll(" ","+");

        //recuperando dadis via Webservice
        YoutubeService youtubeService = retrofit.create(YoutubeService.class);
        //Call<Resultado> resultadoCall = youtubeService.recuperarVideos(); MANEIRA ANTIGA
/*        youtubeService.recuperarVideos(
                "snippet",
                "date",
                "20",
                YoutubeConfig.GOOGLE_API_KEY,
                YoutubeConfig.CANAL_ID,
                q
        ).enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {

                //VERIFICANDO SE A RESPOSTA DA CHAMADA TEVE SUCESSO
                if (response.isSuccessful()){

                    //recuperar o corpo de resultados
                    resultado = response.body();
                    //recuperar uma lista de valores de resultado
                    itemsList = resultado.getItems();
                    configurarRecyclerView();

                }

            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
                Log.i("Resultado","Resultado: "+call.request()+", "+t.getMessage());
            }
        });*/

        youtubeService.recuperarVideosPorRegiao("snippet","date","50",YoutubeConfig.GOOGLE_API_KEY,q,"Br").enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                //VERIFICANDO SE A RESPOSTA DA CHAMADA TEVE SUCESSO
                if (response.isSuccessful()){

                    //recuperar o corpo de resultados
                    resultado = response.body();
                    //recuperar uma lista de valores de resultado
                    itemsList = resultado.getItems();
                    configurarRecyclerView();

                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
                Toast.makeText(YoutubeApiActivity.this, "Erro "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pesquisa,menu);

        MenuItem item = menu.findItem(R.id.pesquisa_menu);
        searchView.setMenuItem(item);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void configurarRecyclerView(){

        adapterYoutubeApi = new AdapterYoutubeApi(itemsList,getContext());
        recyclerViewYoutubeApi.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewYoutubeApi.setHasFixedSize(true);
        recyclerViewYoutubeApi.setAdapter(adapterYoutubeApi);

        //configurar eventos de clique
        recyclerViewYoutubeApi.addOnItemTouchListener(new RecyclerItemClickListener(
                getContext(), recyclerViewYoutubeApi, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Items videoItems = itemsList.get(position);
                    String idVideo = videoItems.getId().getVideoId();
                    //passar valores de item para nova activity
                    Intent i  = new Intent(getContext(), PlayerActivity.class);
                    i.putExtra("VideoId",idVideo);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);

                }

                @Override
                public void onLongItemClick(View view, int position) {

                }

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            }
                 )
            {
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                    Log.i("Pesquisa",searchWrd);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);    }
}


















