package ar.com.ada.api.questionados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.questionados.entities.Categoria;
import ar.com.ada.api.questionados.models.response.GenericResponse;
import ar.com.ada.api.questionados.service.CategoriaService;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    // GET /categorias -> traer categorias
    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> traerCategorias() {
        return ResponseEntity.ok(service.traerCategorias());
    }

    // POST /categorias -> crea una categoria
    @PostMapping ("/categorias")
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria){
    
   GenericResponse respuesta = new GenericResponse();

   service.crearCategoria(categoria);

   respuesta.isOk = true;
   respuesta.id = categoria.getCategoriaId();
   respuesta.message = "La categoria ha sido creada correctamente.";

   return ResponseEntity.ok(respuesta);


    }
}
