package br.com.tfleet.tests.olxtestes;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private YouTubePlayerView youTubePlayerView;

    //usar chave para inicializar player
    private static final String GOOGLE_API_KEY = "AIzaSyBtPOB3RZzRQjH9GAfe83IQleOqEWa8Bco";
    private YouTubePlayer.PlaybackEventListener playbackEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubePlayerView = findViewById(R.id.Youtube_view_id);
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
        }else{
            youTubePlayer.setPlaybackEventListener(playbackEventListener);
            Log.i("Restaurado","Estado do video: "+foiRestaurado);
        }


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(getContext(), "Erro ao iniciar."+youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
    }

     protected Context getContext(){
        return MainActivity.this;
     }

}
