package com.jsm.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsm.model.Categoria;
import com.jsm.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource extends ResourceImpl<Categoria, CategoriaService> {

}
