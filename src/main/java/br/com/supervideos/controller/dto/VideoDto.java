package br.com.supervideos.controller.dto;

import br.com.supervideos.modelo.Videos;

import java.util.List;
import java.util.stream.Collectors;

public class VideoDto {

    private Long id;
    private String titulo;
    private String descricao;
    private String url;

    public VideoDto(Videos videos){
        this.id = videos.getId();
        this.descricao = videos.getDescricao();
        this.titulo = videos.getTitulo();
        this.url = videos.getUrl();
    }

    public static List<VideoDto> convert(List<Videos> videos) {
        return videos.stream().map(VideoDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrl() {
        return url;
    }
}
