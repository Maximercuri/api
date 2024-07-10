package med.voll.api.infra.seguridad;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.voll.api.dominio.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.seguridad.secret}")
    private String secretAPI;

    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretAPI);
            return JWT.create()
                    .withIssuer("voll med")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaDeExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

//    public String getSubject(String token){
//        if (token == null){
//            throw new RuntimeException("Token Vacío No Valido");
//        }
//        DecodedJWT verifier = null;
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(secretAPI);
//            verifier = JWT.require(algorithm)
//                    .withIssuer("voll med")
//                    .build()
//                    .verify(token);
//            verifier.getSubject();
//        } catch (JWTVerificationException exception){
//            System.out.println(exception.toString());
//        }
//        if(verifier.getSubject() == null){
//            throw new RuntimeException("Verificador Invalido");
//        }
//        return verifier.getSubject();
//    }

    public String getSubject(String token) {

        if (token == null) {
            throw new RuntimeException("Token Vacío No Valido");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretAPI);
        DecodedJWT verifier = null;
        try {
            verifier = JWT.require(algorithm)
                    .withIssuer("voll med")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Verificación de JWT fallida", exception);
        }

        if (verifier == null || verifier.getSubject() == null) {
            throw new RuntimeException("Verificador Invalido");
        }

        return verifier.getSubject();
    }


    private Instant generarFechaDeExpiracion() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-05:00"));
    }


}
