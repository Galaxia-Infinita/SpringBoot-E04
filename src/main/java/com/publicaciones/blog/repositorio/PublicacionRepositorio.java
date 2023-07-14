package com.publicaciones.blog.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.publicaciones.blog.entidades.Publicacion;

public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long> {
	
}
