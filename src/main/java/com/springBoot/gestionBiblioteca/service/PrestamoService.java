package com.springBoot.gestionBiblioteca.service;

import com.springBoot.gestionBiblioteca.model.Libro;
import com.springBoot.gestionBiblioteca.model.Prestamo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrestamoService extends BaseService<Prestamo, Integer> {
    public void registro(String documento, Libro libro);

    public List<Prestamo> prestamosUsuario(Integer id);

    public void eliminarPrestamo(Integer id);
}
