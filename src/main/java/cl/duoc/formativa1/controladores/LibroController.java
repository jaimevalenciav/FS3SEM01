package cl.duoc.formativa1.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.formativa1.entidades.Libro;
import cl.duoc.formativa1.servicios.LibroService;
import io.micrometer.core.ipc.http.HttpSender.Response;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<Libro> obtenerTodosLosLibros() {
        return libroService.obtenerTodosLosLibros();
    }
    

    @GetMapping("/{id}")
    public Optional<Libro> getLibroById(@PathVariable int id) {
        return libroService.obtenerLibroPorId(id);
    }   

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroService.guardarLibro(libro);
        return ResponseEntity.ok(nuevoLibro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable int id, @RequestBody Libro libroDetalles) {
        Optional<Libro> libroExistente = libroService.obtenerLibroPorId(id);
        if (libroExistente.isPresent()) {
            Libro libroActualizado = libroExistente.get();
            libroActualizado.setTitulo(libroDetalles.getTitulo());
            libroActualizado.setAutor(libroDetalles.getAutor());
            libroActualizado.setAnio(libroDetalles.getAnio());
            Libro libroGuardado = libroService.guardarLibro(libroActualizado);
            return ResponseEntity.ok(libroGuardado);
        } else {
            return ResponseEntity.notFound().build();   
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable int id) {
        Optional<Libro> libroExistente = libroService.obtenerLibroPorId(id);
        if (libroExistente.isPresent()) {
            libroService.eliminarLibro(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
