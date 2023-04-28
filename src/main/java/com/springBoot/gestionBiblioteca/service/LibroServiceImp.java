package com.springBoot.gestionBiblioteca.service;

import com.springBoot.gestionBiblioteca.model.Libro;
import com.springBoot.gestionBiblioteca.model.Status;
import com.springBoot.gestionBiblioteca.repository.BaseRepository;
import com.springBoot.gestionBiblioteca.repository.LibroRepository;
import com.springBoot.gestionBiblioteca.repository.StatusRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroServiceImp extends BaseServiceImp<Libro, Integer> implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private StatusRepository statusRepository;

    private static final int STATUS_DISPONIBLE = 1;
    private static final int STATUS_PRESTADO = 2;

    public LibroServiceImp(BaseRepository<Libro, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional
    public List<Libro> disponibles() {

        return libroRepository.findAll().stream()
                .filter(v -> v.getStatus().getId() == STATUS_DISPONIBLE)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Libro estado(Integer id) {

        Libro libroDevuelto = new Libro();
        if (libroRepository.existsById(id)) {
            Libro libroPrestado = libroRepository.findById(id).get();
            libroDevuelto.setId(libroPrestado.getId());
            libroDevuelto.setNombre(libroPrestado.getNombre());
            if (libroPrestado.getStatus().getId() == STATUS_PRESTADO) {
                libroDevuelto.setStatus(statusRepository.findById(STATUS_DISPONIBLE).get());
            } else {
                libroDevuelto.setStatus(statusRepository.findById(STATUS_PRESTADO).get());
            }
            libroRepository.save(libroDevuelto);
        }
        return libroDevuelto;

    }

    @Override
    public Libro save(Libro libro) throws Exception {

        Status status = statusRepository.findById(1).get();
        libro.setStatus(status);
        libro = libroRepository.save(libro);
        return libro;

    }
}
