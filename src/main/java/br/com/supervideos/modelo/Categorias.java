package br.com.supervideos.modelo;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categorias {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;
    private String titulo;
    private String cor;
    @OneToMany
    private List<Videos> videos;

    public Categorias(String titulo, String cor) {
        this.titulo = titulo;
        this.cor = cor;
    }

    public Categorias() {
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
