package com.springBoot.gestionBiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<E,ID> extends JpaRepository<E, ID> {

}
