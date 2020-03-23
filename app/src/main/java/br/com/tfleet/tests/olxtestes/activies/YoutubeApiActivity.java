package br.com.tfleet.tests.olxtestes.activies;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.tfleet.tests.olxtestes.R;
import br.com.tfleet.tests.olxtestes.adapters.AdapterYoutubeApi;
import br.com.tfleet.tests.olxtestes.model.Video;

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
    private List<Video> videoList =  new ArrayList<>();;
    private AdapterYoutubeApi adapterYoutubeApi = new AdapterYoutubeApi(videoList, getContext());
    private MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_api);
        //elementos
        RecyclerView recyclerViewYoutubeApi = findViewById(R.id.recyclerView_Youtubevideos_id);

        carregareElementos();
        recuperarVideos();
        //configurar RecyclerView e Adapter
        recyclerViewYoutubeApi.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewYoutubeApi.setHasFixedSize(true);
        recyclerViewYoutubeApi.setAdapter(adapterYoutubeApi);

        //metodo de pesquisa
        pesquisaGeral();




    }

    private void carregareElementos(){
        Toolbar toolbar = findViewById(R.id.toolbar_YoutubeApi);
        toolbar.setTitle("YouTube Api");
        setSupportActionBar(toolbar);
        searchView = findViewById(R.id.pesquisaIcon_Youtube_id);


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
        Video video = new Video();
        video.setTitulo("Video teste Api 1");
        video.setDescricao("Video de teste para reproducção de um teste");
        videoList.add(video);

        Video video2 = new Video();
        video2.setTitulo("Video teste Api 2");
        video2.setDescricao("Video de teste para reproducção de um teste");
        videoList.add(video2);

        Video video3 = new Video();
        video3.setTitulo("Video teste Api 3");
        video3.setDescricao("Video de teste para reproducção de um teste");
        videoList.add(video3);

        Video video4 = new Video();
        video4.setTitulo("Video teste Api 4");
        video4.setDescricao("Video de teste para reproducção de um teste");
        videoList.add(video4);

        Video video5 = new Video();
        video5.setTitulo("Video teste Api 5");
        video5.setDescricao("Video de teste para reproducção de um teste");
        videoList.add(video5);

        Video video6 = new Video();
        video6.setTitulo("Video teste Api 6");
        video6.setDescricao("Video de teste para reproducção de um teste");
        videoList.add(video6);

        Video video7 = new Video();
        video7.setTitulo("Video teste Api 7");
        video7.setDescricao("Video de teste para reproducção de um teste");
        videoList.add(video7);



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


















