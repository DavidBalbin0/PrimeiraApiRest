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
import java.util.Optional;


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
    public ResponseEntity<VideoDto> detalhar(@PathVariable Long id){

        Optional<Videos> videos = videoRepository.findById(id);
        if(videos.isPresent()){
            return ResponseEntity.ok(new VideoDto(videos.get()));
        }else{
            return ResponseEntity.notFound().build();
        }

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
        Optional<Videos> optionalVideos = videoRepository.findById(id);
        if(optionalVideos.isPresent()){
            Videos videos = form.atualiza(id, videoRepository);
            return ResponseEntity.ok(new VideoDto(videos));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<VideoDto> remove(@PathVariable Long id){
        Optional<Videos> optionalVideos = videoRepository.findById(id);
        if(optionalVideos.isPresent()){
            videoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
