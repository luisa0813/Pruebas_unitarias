package com.springBoot.gestionBiblioteca.service;


import com.springBoot.gestionBiblioteca.model.Usuario;
import com.springBoot.gestionBiblioteca.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UsuarioServiceImpTest {
    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    PrestamoServiceImp prestamoServiceImp;
    @InjectMocks
    UsuarioServiceImp usuarioServiceImp;


    @BeforeEach
    void setUp() {
        //usuarioRepository = mock(UsuarioRepository.class);


    }

    @Test
    public  void prestarLibro(){
        //usuarioServiceImp= new UsuarioServiceImp()
        Usuario res=usuarioServiceImp.prestarLibro(1,"123");
        Assertions.assertEquals("123", res.getDocumento());


    }


}