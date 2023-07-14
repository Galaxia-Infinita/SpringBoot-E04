package com.publicaciones.blog.entidades;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
//esta tabla se va a generar en la BD(la BD del mysql está vacia) se ha usado unique para
//evitar datos repetidos( en este caso no repetir el titulo de la publicacion)
@Table(name = "publicaciones", uniqueConstraints = { @UniqueConstraint(columnNames = { "titulo" }) })
public class Publicacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "titulo", nullable = false)
	private String titulo;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "contenido", nullable = false)
	private String contenido;
	
	//una publicacion puede tener muchos comentarios
	//JsonBackReference para eliminar problemas de recursion infinita ignorando serializacion
	@JsonBackReference
	//cascade=CascadeType.ALL,orphanRemoval=true para eliminar todos datos asociados al objeto
	@OneToMany(mappedBy="publicacion", cascade=CascadeType.ALL,orphanRemoval=true)
	private Set<Comentario> comentarios= new HashSet<>();

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
	
	//para mostrar publicacion junto a sus comentarios
	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	//constructor vacio
	public Publicacion() {
		super();
	}

	//constructor con todo
	public Publicacion(Long id, String titulo, String descripcion, String contenido) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.contenido = contenido;
	}

}
