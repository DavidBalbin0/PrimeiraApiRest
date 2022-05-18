package br.com.supervideos.controller.form;

import br.com.supervideos.modelo.Categorias;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoriaForm {

    @NotNull @NotEmpty @Length(min = 5, max = 15)
    private String titulo;
    @NotNull @NotEmpty @Length(min = 5, max = 15)
    private String cor;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Categorias converter() {
        return new Categorias(titulo, cor);
    }
}
