/*
 * Copyright (c) 2018 Antonio Silva
 * Copyright (c) 2018 Keslley Lima
 * MIT License.
 */
package gt.dsdm.es.inf.br.ufg.gt_app.model;

public class Ocorrencia {

    private int id;
    private String titulo;
    private String descricao;
    private String status;
    private String localizacao;
    private String categoria;

    public Ocorrencia(String titulo, String descricao, String status, String localizacao, String categoria) {
        this.id = 222;//TODO gerar número randômico aqui
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.localizacao = localizacao;
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String toJson() {
        return "{" +
                "\"id\":" + id +
                ", \"titulo\":\"" + titulo + '\"' +
                ", \"descricao\":\"" + descricao + '\"' +
                ", \"status\":\"" + status + '\"' +
                ", \"localizacao\":\"" + localizacao + '\"' +
                ", \"categoria\":\"" + categoria + '\"' +
                '}';
    }
}
