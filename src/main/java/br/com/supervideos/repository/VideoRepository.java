package br.com.supervideos.repository;

import br.com.supervideos.modelo.Videos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Videos, Long> {
    List<Videos> findAllByTitulo(String titulo);
}
