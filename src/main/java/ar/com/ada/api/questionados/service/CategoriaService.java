package ar.com.ada.api.questionados.service;

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
        if (existe(categoria.getCategoriaId()))
            return false;

        repo.save(categoria);
        
        return true;
    }

    public Categoria buscarPorId(Integer id) {
        return repo.findByCategoriaId(id);
    }

    public boolean existe(Integer id) {
        Categoria categoria = buscarPorId(id);
        return categoria != null;
    }

}
