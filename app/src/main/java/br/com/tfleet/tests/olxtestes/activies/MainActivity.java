package br.com.tfleet.tests.olxtestes.activies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import br.com.tfleet.tests.olxtestes.R;

public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    //usar chave para inicializar player
    private static final String GOOGLE_API_KEY = "AIzaSyBtPOB3RZzRQjH9GAfe83IQleOqEWa8Bco";
    private YouTubePlayer.PlaybackEventListener playbackEventListener;
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.Youtube_view_id);
        youTubePlayerView.initialize(GOOGLE_API_KEY, this );

        //carregar o playback de eventos de monitoramento de video
        playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onPlaying() {
                Toast.makeText(getContext(), "Video está em ONPLAYNG.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPaused() {
                Toast.makeText(getContext(), "Video pausado.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopped() {
                Toast.makeText(getContext(), "Video Parou.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBuffering(boolean b) {
                Toast.makeText(getContext(), "Video está carregando. "+b, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSeekTo(int i) {
                Toast.makeText(getContext(), "Video em Carregamento.", Toast.LENGTH_SHORT).show();
            }
        };

        //verifica as mudanças no carregamento do video
        playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {
                Toast.makeText(MainActivity.this, "Carregando.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoaded(String s) {
                Toast.makeText(MainActivity.this, "Carregado.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdStarted() {
                Toast.makeText(MainActivity.this, "Anuncios inicializados.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoStarted() {
                Toast.makeText(MainActivity.this, "Video inicializado.", Toast.LENGTH_SHORT).show();            }

            @Override
            public void onVideoEnded() {
                Toast.makeText(MainActivity.this, "Finalizado.", Toast.LENGTH_SHORT).show();            }

            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {
                Toast.makeText(MainActivity.this, "Erro: "+errorReason, Toast.LENGTH_SHORT).show();            }
        };

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean foiRestaurado) {
        Toast.makeText(getContext(), "Iniciado com sucesso.", Toast.LENGTH_SHORT).show();

        //carregar o codigo de um video. Carrega o video automaticamente
        //youTubePlayer.loadVideo("52Io_FokIy4");

        if (!foiRestaurado){
            //carregar o codigo de um video. Nao carrega o video automaticamente
            //youTubePlayer.cueVideo("52Io_FokIy4");

            //para montagem de playlists
            youTubePlayer.cuePlaylist("PLWz5rJ2EKKc9mxIBd0DRw9gwXuQshgmn2");
            //monitorando os eventos no video
            youTubePlayer.setPlaybackEventListener(playbackEventListener);
            youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        }else{
            youTubePlayer.setPlaybackEventListener(playbackEventListener);
            youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
            Log.i("Restaurado","Estado do video: "+foiRestaurado);
        }


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(getContext(), "Erro ao iniciar."+youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
    }

    public void acessarApi(View view){
        Intent intent = new Intent(getContext(),YoutubeApiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

     protected Context getContext(){
        return MainActivity.this;
     }

}
