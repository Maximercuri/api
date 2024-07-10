package med.voll.api.controladores;

import jakarta.validation.Valid;
import med.voll.api.dominio.usuario.DatosAutenticaciónUsuario;
import med.voll.api.dominio.usuario.Usuario;
import med.voll.api.infra.seguridad.DdatosJWTtoken;
import med.voll.api.infra.seguridad.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UsuariosController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping()
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticaciónUsuario datosAutenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(), datosAutenticacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok().body(new DdatosJWTtoken(JWTtoken));
    }
    };
