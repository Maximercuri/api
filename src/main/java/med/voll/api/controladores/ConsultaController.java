package med.voll.api.controladores;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dominio.consulta.AgendaDeConsultaService;
import med.voll.api.dominio.consulta.DatosAgendarConsulta;
import med.voll.api.dominio.consulta.DatosDetallesDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService consultaService;

    @PostMapping()
    @Transactional

    //Formato Para Escribir un LocalDateTime: YYYY-MM-ddThh:mm
    public ResponseEntity<DatosDetallesDeConsulta> agendarConsulta(@RequestBody @Valid DatosAgendarConsulta datosAgendarConsulta) {

        consultaService.agendar(datosAgendarConsulta);

        return ResponseEntity.ok(new DatosDetallesDeConsulta(null, null, null, null));
    }
}
