package br.com.tfleet.tests.olxtestes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import br.com.tfleet.tests.olxtestes.R;
import br.com.tfleet.tests.olxtestes.model.Video;

public class AdapterYoutubeApi extends RecyclerView.Adapter<AdapterYoutubeApi.MyViewHolder> {
    private List<Video> videoList;
    private Context context;

    public AdapterYoutubeApi(List<Video> videoList, Context context) {
        this.videoList = videoList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_youtube_api,parent,false);
        return new AdapterYoutubeApi.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Video videos = videoList.get(position);
        holder.tituloVideo.setText(videos.getTitulo());
        holder.descricaoVideo.setText(videos.getDescricao());


    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView capa;
        TextView tituloVideo;
        TextView descricaoVideo;
        TextView dataVideo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            capa = itemView.findViewById(R.id.capa_YoutubeApi_id_Adapter);
            tituloVideo = itemView.findViewById(R.id.titulo_YoutubeApi_id_Adapter);
            descricaoVideo = itemView.findViewById(R.id.descricao_YoutubeApi_id_Adapter);

        }
    }
}
