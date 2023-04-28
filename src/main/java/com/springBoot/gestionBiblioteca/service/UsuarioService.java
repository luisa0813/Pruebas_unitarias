package com.springBoot.gestionBiblioteca.service;

import com.springBoot.gestionBiblioteca.model.Prestamo;
import com.springBoot.gestionBiblioteca.model.Usuario;

import java.util.List;

public interface UsuarioService extends BaseService<Usuario, Integer> {
    public Usuario prestarLibro(Integer id, String documento);

    public Usuario buscarConDocumento(String documento);

    public List<Prestamo> prestamos(Integer id);

    public Usuario devolverLibro(Integer id, String documento);
}
