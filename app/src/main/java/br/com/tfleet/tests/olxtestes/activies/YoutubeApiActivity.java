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
import br.com.tfleet.tests.olxtestes.model.Items;
import br.com.tfleet.tests.olxtestes.model.Resultado;
import br.com.tfleet.tests.olxtestes.model.Video;
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

        //recuperar os videos
        recuperarVideos();

        //metodo de pesquisa
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

            }

            @Override
            public void onSearchViewClosed() {

            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
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

    private void recuperarVideos(){

        //recuperando dadis via Webservice
        YoutubeService youtubeService = retrofit.create(YoutubeService.class);
        //Call<Resultado> resultadoCall = youtubeService.recuperarVideos(); MANEIRA ANTIGA
        youtubeService.recuperarVideos(
                "snippet",
                "date",
                "20",
                YoutubeConfig.GOOGLE_API_KEY,
                YoutubeConfig.CANAL_ID

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




                    Log.i("Resultado","Resultado: "+resultado.getItems().get(0).getSnippet().getThumbnails().getMedium().getHeight());
                }

            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
                Log.i("Resultado","Resultado: "+call.request()+", "+t.getMessage());
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pesquisa,menu);

        MenuItem item = menu.findItem(R.id.pesquisa_menu);
        searchView.setMenuItem(item);

        //ativar voiceSearch no codigo
        searchView.setVoiceSearch(true); //or false
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


















