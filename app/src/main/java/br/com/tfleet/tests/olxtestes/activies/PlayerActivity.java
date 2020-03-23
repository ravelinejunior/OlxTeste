package br.com.tfleet.tests.olxtestes.activies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import br.com.tfleet.tests.olxtestes.R;
import br.com.tfleet.tests.olxtestes.helper.YoutubeConfig;

public class PlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private YouTubePlayerView youTubePlayerView;
    private String idVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        carregarVideoPlayer();

        //recuperar idVideo
        Bundle bundle = getIntent().getExtras();
        if ( bundle != null){
            idVideo = bundle.getString("VideoId");
            youTubePlayerView.initialize(YoutubeConfig.GOOGLE_API_KEY,this);
        }
    }

    public void carregarVideoPlayer(){
        youTubePlayerView = findViewById(R.id.YoutubePlayer_view_id);
    }

    public Context getContext(){
        return PlayerActivity.this;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        // para carregar video com tela cheia
            youTubePlayer.setFullscreen(true);
            youTubePlayer.setShowFullscreenButton(false);
            youTubePlayer.cueVideo(idVideo);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
