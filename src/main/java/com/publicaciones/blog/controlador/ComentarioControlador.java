package com.publicaciones.blog.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.publicaciones.blog.dto.ComentarioDTO;
import com.publicaciones.blog.servicio.ComentarioServicio;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ComentarioControlador {
	@Autowired
	private ComentarioServicio comentarioServicio;
	
	//metodo para mostrar comentarios por publicacion
	@GetMapping("/publicaciones/{publicacionId}/comentarios")
	public List<ComentarioDTO> listarComentariosPorIdPublicacion(@PathVariable(value="publicacionId") Long publicacionId){
		return comentarioServicio.obtenerComentariosPorPublicacionId(publicacionId);
	}
	
	//metodo para buscar comentario por id, por publicacion
	@GetMapping("/publicaciones/{publicacionId}/comentarios/{id}")
	public ResponseEntity<ComentarioDTO> obtenerComentarioPorId(@PathVariable(value="publicacionId") Long publicacionId, @PathVariable(value="id") Long comentarioId){
		ComentarioDTO comentarioDTO=comentarioServicio.obtenerComentarioPorId(publicacionId, comentarioId);
		return new ResponseEntity<>(comentarioDTO, HttpStatus.OK);
	}
	
	//metodo para postear comentarios por publicacion
	@PostMapping("/publicaciones/{publicacionId}/comentarios")
	public ResponseEntity<ComentarioDTO> guardarComentario(@PathVariable(value="publicacionId") long publicacionId,@Valid @RequestBody ComentarioDTO comentarioDTO){
		return new ResponseEntity<>(comentarioServicio.crearComentario(publicacionId, comentarioDTO), HttpStatus.CREATED);
	}
	
	//actualizar comentarios que hay dentro de una publicacion
	@PutMapping("/publicaciones/{publicacionId}/comentarios/{id}")
	public ResponseEntity<ComentarioDTO> actualizarComentario(@PathVariable(value="publicacionId") Long publicacionId, @PathVariable(value="id") Long comentarioId,@Valid @RequestBody ComentarioDTO comentarioDTO){
		ComentarioDTO comentarioActualizado= comentarioServicio.actualizarComentario(publicacionId, comentarioId, comentarioDTO);
		return new ResponseEntity<>(comentarioActualizado, HttpStatus.OK);
	}
	
	@DeleteMapping("/publicaciones/{publicacionId}/comentarios/{id}")
	public ResponseEntity<String> eliminarComentario(@PathVariable(value="publicacionId") Long publicacionId, @PathVariable(value="id") Long comentarioId){
		comentarioServicio.eliminarComentario(publicacionId, comentarioId);
		return new ResponseEntity<>("Comentario eliminado con exito",HttpStatus.OK);
	}
	
}
