package br.com.supervideos.controller.form;

import br.com.supervideos.modelo.Videos;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VideosForm {

    @NotNull @NotEmpty @Length(min = 5, max = 25)
    private String titulo;
    @NotNull @NotEmpty @Length(min = 5, max = 50)
    private String descricao;
    @NotNull @NotEmpty @Length(min = 5, max = 50)
    private String url;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Videos converter() {
       return new Videos(titulo, descricao, url);
    }
}
