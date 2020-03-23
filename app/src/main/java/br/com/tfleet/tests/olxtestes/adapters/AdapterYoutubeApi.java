package br.com.tfleet.tests.olxtestes.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import br.com.tfleet.tests.olxtestes.R;
import br.com.tfleet.tests.olxtestes.model.Items;
import br.com.tfleet.tests.olxtestes.model.Video;

public class AdapterYoutubeApi extends RecyclerView.Adapter<AdapterYoutubeApi.MyViewHolder> {
    private List<Items> videoList;
    private Context c;

    public AdapterYoutubeApi(List<Items> videoList, Context context) {
        this.videoList = videoList;
        this.c = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_youtube_api,parent,false);
        return new AdapterYoutubeApi.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Items videos = videoList.get(position);
        holder.capa.setImageURI(Uri.parse(videos.getSnippet().getThumbnails().getHigh().getUrl()));
        holder.tituloVideo.setText(videos.getSnippet().getTitle());
        holder.descricaoVideo.setText(videos.getSnippet().getDescription());
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
