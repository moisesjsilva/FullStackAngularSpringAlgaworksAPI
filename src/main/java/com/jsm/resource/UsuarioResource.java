//package com.jsm.resource;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.jsm.security.model.Usuario;
//import com.jsm.service.UsuarioService;
//
//@RestController
//@RequestMapping("/usuarios")
//public class UsuarioResource extends ResourceImpl<Usuario, UsuarioService> {
//	
//	
//	@GetMapping(params= {"username"})
//	public ResponseEntity<Usuario> findByUsernameAndEnableTrue(@RequestParam String username){
//	  Usuario u =	getService().findByUsernameAndEnableTrue(username).get();
//	  return ResponseEntity.ok(u);
//	}
//
//}
