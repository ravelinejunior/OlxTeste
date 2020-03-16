package br.com.tfleet.tests.olxtestes.model;

public class Video {

    private String descricao = "";
    private String titulo = "";
    private String data = "";
    private String capaImagem = "";
    private String videoID = "";

    public Video() {

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCapaImagem() {
        return capaImagem;
    }

    public void setCapaImagem(String capaImagem) {
        this.capaImagem = capaImagem;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }
}
