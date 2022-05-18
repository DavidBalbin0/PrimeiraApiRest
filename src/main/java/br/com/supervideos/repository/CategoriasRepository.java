package br.com.supervideos.repository;

import br.com.supervideos.modelo.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
}
