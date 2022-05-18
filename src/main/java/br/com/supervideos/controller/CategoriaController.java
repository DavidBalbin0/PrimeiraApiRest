package br.com.supervideos.controller;


import br.com.supervideos.controller.dto.CategoriasDto;
import br.com.supervideos.controller.dto.VideoDto;
import br.com.supervideos.controller.form.AtualizacaoCategoriaForm;
import br.com.supervideos.controller.form.AtualizacaoVideosForm;
import br.com.supervideos.controller.form.CategoriaForm;
import br.com.supervideos.modelo.Categorias;
import br.com.supervideos.modelo.Videos;
import br.com.supervideos.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @GetMapping
    public List<CategoriasDto> ListaCategorias() {

        List<Categorias> categorias = categoriasRepository.findAll();
        return CategoriasDto.converteCategoria(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriasDto> detalhar(@PathVariable Long id) {
        Optional<Categorias> categoria = categoriasRepository.findById(id);
        if (categoria.isPresent()) {
            return ResponseEntity.ok(new CategoriasDto(categoria.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CategoriasDto> cadastrar(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriBuilder) {
        Categorias categorias = form.converter();
        categoriasRepository.save(categorias);

        URI uri = uriBuilder.path("categorias/{id}").buildAndExpand(categorias.getIdCategoria()).toUri();
        return ResponseEntity.created(uri).body(new CategoriasDto(categorias));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriasDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoCategoriaForm form) {
        Optional<Categorias> optionalCategorias = categoriasRepository.findById(id);
        if (optionalCategorias.isPresent()) {
            Categorias categorias = form.atualiza(id, categoriasRepository);
            return ResponseEntity.ok(new CategoriasDto(categorias));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriasDto> remove(@PathVariable Long id){
        Optional<Categorias> optionalCategorias = categoriasRepository.findById(id);
        if(optionalCategorias.isPresent()){
            categoriasRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
