package br.com.supervideos.controller;

import br.com.supervideos.controller.dto.VideoDto;
import br.com.supervideos.controller.form.AtualizacaoVideosForm;
import br.com.supervideos.controller.form.VideosForm;
import br.com.supervideos.modelo.Videos;
import br.com.supervideos.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/videos")
public class VideosController {

    @Autowired
    private VideoRepository videoRepository;

   @GetMapping
    public List<VideoDto> lista(String titulo){
        if(titulo == null) {
            List<Videos> videos = videoRepository.findAll();
            return VideoDto.convert(videos);
        } else {
            List<Videos> videos = videoRepository.findAllByTitulo(titulo);
            return VideoDto.convert(videos);
        }

    }


    @GetMapping(value = "/{id}")
    public List<VideoDto> pesquisaPorUri(@PathVariable("id") Long id){
        List<Videos> videos =  videoRepository.findAllById(Collections.singleton(id));
        return VideoDto.convert(videos);
    }

    @PostMapping
    public ResponseEntity<VideoDto> cadastrar(@RequestBody @Valid VideosForm form, UriComponentsBuilder uriBuilder){
       Videos videos = form.converter();
       videoRepository.save(videos);

        URI uri = uriBuilder.path("videos/{id}").buildAndExpand(videos.getId()).toUri();
       return ResponseEntity.created(uri).body(new VideoDto(videos));

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VideoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoVideosForm form){
       Videos videos = form.atualiza(id, videoRepository);

       return ResponseEntity.ok(new VideoDto(videos));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<VideoDto> remove(@PathVariable Long id){

       videoRepository.deleteById(id);
       return ResponseEntity.ok().build();

    }

}
