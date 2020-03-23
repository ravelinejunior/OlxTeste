package br.com.tfleet.tests.olxtestes.services;

import br.com.tfleet.tests.olxtestes.helper.YoutubeConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    //criando metodo estatico para chamar os atributos iniciais da API
    public static Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(YoutubeConfig.URL_BASE_YOUTUBE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
