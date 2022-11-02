package com.logistica.proyecto.control;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.logistica.proyecto.entidad.Rol;
import com.logistica.proyecto.interfaces.InterfacesSimple;
 

@Controller
@RequestMapping("/Rol")
public class MarcaControl {
	String carpeta = "Rol";
	@Autowired
	private InterfacesSimple<Rol> Service;
 
	
	
	@RequestMapping({"/"})
	public String inicio(Model model) {
		model.addAttribute("titulo", carpeta);
		model.addAttribute("RutaNuevo", "/" + carpeta + "/crear/"); // Crear
		model.addAttribute("RutaEliminar", "/" + carpeta + "/eliminar/"); // Eliminar
		model.addAttribute("RutaEditar", "/" + carpeta + "/editar/"); // Editar
		model.addAttribute("lista", Service.obtenerLista());
	  
		return "/" + carpeta + "/listado";
	}

	@GetMapping("/crear/")
	public String crear(Model model) {
		// instanciar entidad Nuevo
		Rol obj = new Rol();
		model.addAttribute("titulo", "Registrar " + carpeta);// titulo
		model.addAttribute("RutaListado", "/" + carpeta + "/"); // RutaListado
		model.addAttribute("RutaGrabar", "/" + carpeta + "/grabar"); // RutaGrabar
		model.addAttribute("entidad", obj);// entidad
		return "/" + carpeta + "/formulario";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
		Rol obj = null;
		if (Service.existe(id) == true) {
			obj = Service.BuscarPorIdEntidad(id);
			model.addAttribute("titulo", "Modificar " + carpeta);
			model.addAttribute("RutaListado", "/" + carpeta + "/"); // RutaListado
			model.addAttribute("RutaGrabar", "/" + carpeta + "/grabar"); // RutaGrabar
			model.addAttribute("entidad", obj);// modificar

			return "/" + carpeta + "/formulario";
		} else {
			return "/" + carpeta + "/error";
		}

	}

	@RequestMapping("/grabar")
	public String guardar(Model model, Rol obj) {
		Service.guardar(obj);
		return "redirect:";
	}

	@RequestMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Integer id) {
		Service.eliminar(id);
		return "redirect:../";
	}

}
