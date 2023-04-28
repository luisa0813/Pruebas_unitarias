package com.springBoot.gestionBiblioteca.service;

import com.springBoot.gestionBiblioteca.model.Libro;
import com.springBoot.gestionBiblioteca.model.Prestamo;
import com.springBoot.gestionBiblioteca.model.Usuario;
import com.springBoot.gestionBiblioteca.repository.BaseRepository;
import com.springBoot.gestionBiblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImp extends BaseServiceImp<Usuario, Integer> implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LibroService libroService;

    @Autowired
    private PrestamoService prestamoService;

    public UsuarioServiceImp(BaseRepository<Usuario, Integer> baseRepository) {
        super(baseRepository);
    }

    public Usuario prestarLibro(Integer id, String documento) {

        Libro libro = libroService.estado(id);
        prestamoService.registro(documento, libro);
        return usuarioRepository.buscarConDocumento(documento);

    }

    @Override
    public Usuario buscarConDocumento(String documento) {

        return usuarioRepository.buscarConDocumento(documento);

    }

    @Override
    public List<Prestamo> prestamos(Integer id) {
        return prestamoService.prestamosUsuario(id);
    }

    @Override
    public Usuario devolverLibro(Integer id, String documento) {
        libroService.estado(id);
        prestamoService.eliminarPrestamo(id);
        return usuarioRepository.buscarConDocumento(documento);
    }
}
