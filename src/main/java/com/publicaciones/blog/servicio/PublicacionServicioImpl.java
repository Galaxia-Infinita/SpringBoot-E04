package com.publicaciones.blog.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.publicaciones.blog.dto.PublicacionDTO;
import com.publicaciones.blog.dto.PublicacionRespuesta;
import com.publicaciones.blog.entidades.Publicacion;
import com.publicaciones.blog.excepciones.ResourceNotFoundException;
import com.publicaciones.blog.repositorio.PublicacionRepositorio;

@Service
public class PublicacionServicioImpl implements PublicacionServicio{
	@Autowired
	private ModelMapper modelMapper;  //para evitar hacer mapeo de DTO a entidad y viceversa
	
	@Autowired
	private PublicacionRepositorio publicacionRepositorio;
	
	@Override
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
		Publicacion publicacion= mapearEntidad(publicacionDTO);
		
		Publicacion nuevaPublicacion= publicacionRepositorio.save(publicacion);
		
		PublicacionDTO publicacionRespuesta= mapearDTO(nuevaPublicacion);
		return publicacionRespuesta;
	}

	@Override
	public PublicacionRespuesta obtenerTodasLasPublicaciones(int numeroDePagina,int medidaDePagina, String ordenarPor, String sortDir) {
		//se ha modificado el metodo para que tenga paginacion y listado asc o desc
		Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending() : Sort.by(ordenarPor).descending();
		
		Pageable pageable=PageRequest.of(numeroDePagina, medidaDePagina,sort);
		Page<Publicacion> publicaciones= publicacionRepositorio.findAll(pageable);
		
		List<Publicacion> listaDePublicaciones= publicaciones.getContent();
		List<PublicacionDTO> contenido= listaDePublicaciones.stream().map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());
		
		PublicacionRespuesta publicacionRespuesta= new PublicacionRespuesta();
		publicacionRespuesta.setContenido(contenido);
		publicacionRespuesta.setNumeroPagina(publicaciones.getNumber());
		publicacionRespuesta.setMedidaPagina(publicaciones.getSize());
		publicacionRespuesta.setTotalElementos(publicaciones.getTotalElements());
		publicacionRespuesta.setTotalPaginas(publicaciones.getTotalPages());
		publicacionRespuesta.setUltima(publicaciones.isLast());
		
		return publicacionRespuesta;
	}

	@Override
	public PublicacionDTO obtenerPublicacionPorId(long id) {
		Publicacion publicacion= publicacionRepositorio
				.findById(id).orElseThrow(()-> new ResourceNotFoundException("Publicacion","id", id));
		return mapearDTO(publicacion);
	}

	@Override
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id) {
		Publicacion publicacion= publicacionRepositorio
				.findById(id).orElseThrow(()-> new ResourceNotFoundException("Publicacion","id", id));
		
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());
		
		Publicacion publicacionActualizada= publicacionRepositorio.save(publicacion);
		return mapearDTO(publicacionActualizada);
	}

	@Override
	public void eliminarPublicacion(long id) {
		Publicacion publicacion= publicacionRepositorio
				.findById(id).orElseThrow(()-> new ResourceNotFoundException("Publicacion","id", id));
		
		publicacionRepositorio.delete(publicacion);
	}

	//convertir entidad a DTO
		private PublicacionDTO mapearDTO(Publicacion publicacion) {
			PublicacionDTO publicacionDTO=modelMapper.map(publicacion, PublicacionDTO.class);
			
			return publicacionDTO;
		}
		
		//convierte de DTO a Entidad
		private Publicacion mapearEntidad(PublicacionDTO publicacionDTO) {
			Publicacion publicacion= modelMapper.map(publicacionDTO, Publicacion.class);
			
			return publicacion;
		}
}





























