package br.com.supervideos.controller.form;

import br.com.supervideos.modelo.Categorias;
import br.com.supervideos.modelo.Videos;
import br.com.supervideos.repository.CategoriasRepository;
import br.com.supervideos.repository.VideoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizacaoCategoriaForm {

    @NotNull @NotEmpty @Length(min = 5, max = 15)
    String titulo;
    @NotNull @NotEmpty @Length(min = 5, max = 15)
    String cor;

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

    public Categorias atualiza(Long id, CategoriasRepository categoriasRepository) {

        Categorias categorias = categoriasRepository.getById(id);
        categorias.setTitulo(this.titulo);
        categorias.setCor(this.cor);

        return categorias;
    }
}
