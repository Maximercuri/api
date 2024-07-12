package med.voll.api.controladores;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dominio.consulta.AgendaDeConsultaService;
import med.voll.api.dominio.consulta.DatosAgendarConsulta;
import med.voll.api.dominio.consulta.DatosCancelacionDeConsulta;
import med.voll.api.dominio.consulta.DatosDetallesDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService consultaService;

    @Transactional
    @PostMapping()
    //Formato Para Escribir un LocalDateTime: YYYY-MM-ddThh:mm
    public ResponseEntity<DatosDetallesDeConsulta> agendarConsulta(@RequestBody @Valid DatosAgendarConsulta datosAgendarConsulta) {

        var respuesta = consultaService.agendar(datosAgendarConsulta);

        return ResponseEntity.ok(respuesta);
    }

    @Transactional
    @DeleteMapping()
    public ResponseEntity cancelarConsulta(@RequestBody @Valid DatosCancelacionDeConsulta consulta) {

        consultaService.cancelar(consulta);

        return ResponseEntity.noContent().build();

    }
}
