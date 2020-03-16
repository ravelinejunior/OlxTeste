package br.com.tfleet.tests.olxtestes.activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.tfleet.tests.olxtestes.R;
import br.com.tfleet.tests.olxtestes.adapters.AdapterYoutubeApi;
import br.com.tfleet.tests.olxtestes.model.Video;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class YoutubeApiActivity extends AppCompatActivity {
    private RecyclerView recyclerViewYoutubeApi;
    private List<Video> videoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_api);
        carregareElementos();
        recuperarVideos();

        //conffigurar RecyclerView e Adapter
        recyclerViewYoutubeApi = new RecyclerView(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewYoutubeApi.setHasFixedSize(true);
        recyclerViewYoutubeApi.setLayoutManager(layoutManager);


        AdapterYoutubeApi adapterYoutubeApi = new AdapterYoutubeApi(videoList, getContext());
        recyclerViewYoutubeApi.setAdapter(adapterYoutubeApi);


    }

    private void carregareElementos(){
        Toolbar toolbar = findViewById(R.id.toolbar_YoutubeApi);
        toolbar.setTitle("YouTube Api");
        setSupportActionBar(toolbar);

        //elementos
        recyclerViewYoutubeApi = findViewById(R.id.recyclerView_Youtubevideos_id);
        videoList = new ArrayList<>();

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
}
