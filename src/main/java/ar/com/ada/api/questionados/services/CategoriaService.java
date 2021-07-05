package ar.com.ada.api.questionados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.questionados.entities.Categoria;
import ar.com.ada.api.questionados.repos.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    public CategoriaRepository repo;

    public List<Categoria> traerCategorias() {
        return repo.findAll();
    }

    public boolean crearCategoria(Categoria categoria) {
        if (existe(categoria.getNombre()))
            return false;

        repo.save(categoria);

        return true;
    }


    public boolean existe(String nombre) {
        return repo.existsByNombre(nombre);
    }

    public void modificarCategoria(Integer id, String nombreNuevo, String descripcionNueva) {
        Categoria categoriaMod = this.buscarCategoria(id);
        categoriaMod.setNombre(nombreNuevo);
        categoriaMod.setDescripcion(descripcionNueva);
        repo.save(categoriaMod);

    }

    public void eliminarCategoriaPorId(Integer id) {
        repo.deleteById(id);

    }

    public Categoria buscarCategoria(Integer categoriaId) {
        Categoria categoria = repo.findById(categoriaId.intValue());

        return categoria;

    }

}
