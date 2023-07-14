package com.publicaciones.blog.dto;

import java.util.Set;

import com.publicaciones.blog.entidades.Comentario;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PublicacionDTO {
	private Long id;
	
	@NotEmpty //para que valide dato vacio
	@Size(min=2, message="El titulo de la publicacion deberia tener al menos dos caracteres")
	private String titulo;
	
	@NotEmpty //para que valide dato vacio
	@Size(min=10, message="La descripcion de la publicacion deberia tener al menos diez caracteres")
	private String descripcion;
	
	private String contenido;
	
	private Set<Comentario> comentarios; 	//para mostrar publicacion junto a sus comentarios

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public PublicacionDTO() {
		super();
	}

	public PublicacionDTO(Long id, String titulo, String descripcion, String contenido) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.contenido = contenido;
	}

}
