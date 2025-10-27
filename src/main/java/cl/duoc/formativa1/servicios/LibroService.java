package cl.duoc.formativa1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cl.duoc.formativa1.entidades.Libro;
import cl.duoc.formativa1.repositorio.LibroRepository;

public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public void eliminarLibro(int id) {
        libroRepository.deleteById(id);
    }

    public Libro obtenerLibroPorId(int id) {
        return libroRepository.findById(id).orElse(null);
    }
    

}
