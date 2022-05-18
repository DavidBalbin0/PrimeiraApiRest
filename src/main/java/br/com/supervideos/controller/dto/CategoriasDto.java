package br.com.supervideos.controller.dto;

import br.com.supervideos.modelo.Categorias;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriasDto {

    private Long idCategoria;
    private String titulo;
    private String cor;

    public CategoriasDto(Categorias categorias) {
        this.idCategoria = categorias.getIdCategoria();
        this.titulo = categorias.getTitulo();
        this.cor = categorias.getCor();
    }

    public static List<CategoriasDto> converteCategoria(List<Categorias> categorias){
        return categorias.stream().map(CategoriasDto::new).collect(Collectors.toList());
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

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
}
