package br.com.tfleet.tests.olxtestes.api;

import br.com.tfleet.tests.olxtestes.model.Resultado;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeService {

    /*
    ESTRUTURA JSON PARA MONTAGEM DA QUERY

    https://www.googleapis.com/youtube/v3/
        search? //passar parametros
        part=snippet
        &order=date
        &maxResults=50
        &key=AIzaSyBtPOB3RZzRQjH9GAfe83IQleOqEWa8Bco
        &channelId=UCTw4_Y7XXuorDLN5fdDWeIQ
        &q=desnvolvimento+pessoal   ---- o mais serve como espaço


        TESTES - https://www.googleapis.com/youtube/v3/search?part=snippet&order=date&maxResults=50&key=AIzaSyBtPOB3RZzRQjH9GAfe83IQleOqEWa8Bco&channelId=UCTw4_Y7XXuorDLN5fdDWeIQ
     */

    //caso queira fazer uma pesquisa
    @GET("search")
    Call<Resultado> recuperarVideos(@Query("part") String part,
                                    @Query("order") String order,
                                    @Query("maxResults") String maxResults,
                                    @Query("key") String key,
                                    @Query("channelId") String channelId,
                                    @Query("q") String q
                    );

    //caso queira fazer uma pesquisa sem canal filtrado
    @GET("search")
    Call<Resultado> recuperarVideosPorRegiao(@Query("part") String part,
                                    @Query("order") String order,
                                    @Query("maxResults") String maxResults,
                                    @Query("key") String key,
                                    @Query("q") String q,
                                    @Query("regionCode") String regionCode
    );

}
