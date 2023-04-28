package com.springBoot.gestionBiblioteca.service;

import com.springBoot.gestionBiblioteca.model.Libro;
import com.springBoot.gestionBiblioteca.model.Multa;
import com.springBoot.gestionBiblioteca.model.Prestamo;
import com.springBoot.gestionBiblioteca.model.Usuario;
import com.springBoot.gestionBiblioteca.repository.BaseRepository;
import com.springBoot.gestionBiblioteca.repository.MultaRepository;
import com.springBoot.gestionBiblioteca.repository.PrestamoRepository;
import com.springBoot.gestionBiblioteca.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrestamoServiceImp extends BaseServiceImp<Prestamo, Integer> implements PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MultaRepository multaRepository;

    public PrestamoServiceImp(BaseRepository<Prestamo, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional
    public void registro(String documento, Libro libro) {

        Usuario usuario = usuarioRepository.buscarConDocumento(documento);
        Multa multa = new Multa();
        multaRepository.save(multa);
        Prestamo prestamo = new Prestamo();
        prestamo.setLibro(libro);
        prestamo.setMulta(multa);
        prestamo.setUsuario(usuarioRepository.buscarConDocumento(documento));
        usuario.setPrestamo(prestamo);
        prestamoRepository.save(prestamo);

    }

    @Override
    @Transactional
    public List<Prestamo> prestamosUsuario(Integer id) {

        List<Prestamo> prestamos = new ArrayList<>();
        prestamoRepository.prestamosUsuario(id).forEach(p -> {
            p.existeMulta(p.getFechaVencimineto(), p.getMulta());
            prestamos.add(p);
        });
        return prestamos;
    }

    @Override
    @Transactional
    public void eliminarPrestamo(Integer id) {
        Prestamo prestamo = prestamoRepository.eliminarPrestamo(id);
        multaRepository.deleteById(prestamo.getMulta().getId());
        prestamoRepository.delete(prestamo);

    }

}
