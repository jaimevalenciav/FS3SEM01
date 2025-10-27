package cl.duoc.formativa1.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.formativa1.entidades.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer> {

}
