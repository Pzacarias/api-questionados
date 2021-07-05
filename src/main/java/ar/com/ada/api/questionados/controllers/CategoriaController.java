package ar.com.ada.api.questionados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.questionados.entities.Categoria;
import ar.com.ada.api.questionados.models.request.InfoNuevaCategoria;
import ar.com.ada.api.questionados.models.response.GenericResponse;
import ar.com.ada.api.questionados.services.CategoriaService;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    // GET /categorias -> traer categorias
    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> traerCategorias() {
        return ResponseEntity.ok(service.traerCategorias());
    }

    // GET Categoría por Id
    @GetMapping("/categoria/{id}")
    public ResponseEntity<Categoria> traerCategoriaPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarCategoria(id));
    }

    // POST /categorias -> crea una categoria
    @PostMapping(value = "/categoria")
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) {

        GenericResponse r = new GenericResponse();

        if (service.crearCategoria(categoria)) {
            r.id = categoria.getCategoriaId();
            r.isOk = true;
            r.message = "Categoria creada con exito";
            return ResponseEntity.ok(r);
        } else {
            r.isOk = false;
            r.message = "Esta categoria ya esta creada";
            return ResponseEntity.badRequest().body(r);
        }
    }

    // PUT /categorias/{id} -> modifica una cateogria
    @PutMapping("/categorias/{id}")
    public ResponseEntity<GenericResponse> modificarCategoria(@PathVariable Integer id,
            @RequestBody InfoNuevaCategoria infoNuevaCategoria) {

        service.modificarCategoria(id, infoNuevaCategoria.descripcionNueva, infoNuevaCategoria.nombreNuevo);
        GenericResponse respuesta = new GenericResponse();

        respuesta.isOk = true;
        respuesta.message = "La categoria ha sido actualizada.";

        return ResponseEntity.ok(respuesta);
    }

    // DELETE /categorias/{id} -> elimina categoria
    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<GenericResponse> eliminarCategoria(@PathVariable Integer id) {
        service.eliminarCategoriaPorId(id);

        GenericResponse respuesta = new GenericResponse();
        respuesta.isOk = true;
        respuesta.message = "La categoria fue eliminada correctamente.";

        return ResponseEntity.ok(respuesta);
    }

}
