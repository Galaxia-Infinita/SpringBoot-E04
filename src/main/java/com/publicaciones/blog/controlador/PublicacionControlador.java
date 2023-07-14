package com.publicaciones.blog.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.publicaciones.blog.dto.PublicacionDTO;
import com.publicaciones.blog.dto.PublicacionRespuesta;
import com.publicaciones.blog.servicio.PublicacionServicio;
import com.publicaciones.blog.utilerias.AppConstantes;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionControlador {

	@Autowired
	private PublicacionServicio publicacionServicio;

	@GetMapping
	public PublicacionRespuesta listarPublicaciones(
			@RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
			@RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
			@RequestParam(value= "sortBy", defaultValue=AppConstantes.ORDENAR_POR_DEFECTO, required= false) String ordenarPor,
			@RequestParam(value= "sortDir", defaultValue=AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required= false) String sortDir) {
		return publicacionServicio.obtenerTodasLasPublicaciones(numeroDePagina,medidaDePagina,ordenarPor,sortDir);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable(name = "id") long id) {
		return ResponseEntity.ok(publicacionServicio.obtenerPublicacionPorId(id));
	}

	@PostMapping
	public ResponseEntity<PublicacionDTO> guardarPublicacion(@Valid  @RequestBody PublicacionDTO publicacionDTO) {
		return new ResponseEntity<>(publicacionServicio.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PublicacionDTO> actualizarPublicaciones(@RequestBody PublicacionDTO publicacionDTO,
			@PathVariable(name = "id") long id) {
		PublicacionDTO publicacionRespuesta = publicacionServicio.actualizarPublicacion(publicacionDTO, id);
		return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") long id) {
		publicacionServicio.eliminarPublicacion(id);
		return new ResponseEntity<>("Publicacion eliminada con exito", HttpStatus.OK);
	}
}
