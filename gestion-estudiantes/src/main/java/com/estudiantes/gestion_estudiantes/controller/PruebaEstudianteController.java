package com.estudiantes.gestion_estudiantes.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.estudiantes.gestion_estudiantes.models.PruebaEstudiante;
import com.estudiantes.gestion_estudiantes.service.IPruebaEstudianteService;
import com.estudiantes.gestion_estudiantes.util.MessageUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prueba/estudiante")
public class PruebaEstudianteController {
    @Autowired
    private IPruebaEstudianteService iPruebaEstudianteService;

    @GetMapping
    public List<PruebaEstudiante> obtenerPruebaEstudiantes(){
        return iPruebaEstudianteService.obtenerPruebaEstudiantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PruebaEstudiante> obtenerEstudiantePorId(@PathVariable Integer id){
        Optional<PruebaEstudiante> estudiante = iPruebaEstudianteService.obtenerEstudiantePorId(id);
        return estudiante.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> guardarPruebaEstudiante(@RequestBody PruebaEstudiante pruebaEstudiante){
        PruebaEstudiante savedEstudiante = iPruebaEstudianteService.guardarPruebaEstudiante(pruebaEstudiante);
        if (savedEstudiante != null){
            return  ResponseEntity.ok().body(MessageUtil.ESTUDIANTE_INGRESADO_EXITOSAMENTE);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageUtil.ERROR_AL_GUARDAR_ESTUDIANTE);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarPruebaEstudiante(
            @PathVariable Integer id, @RequestBody PruebaEstudiante pruebaEstudiante){
        PruebaEstudiante updateEstudiante = iPruebaEstudianteService.actualizarPruebaEstudiante(id, pruebaEstudiante);
        if(updateEstudiante != null){
            return ResponseEntity.ok().body(MessageUtil.ESTUDIANTE_ACTUALIZADO_EXITOSAMENTE);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtil.ESTUDIANTE_NO_ENCONTRADO);
    }

    //Eliminar estudiante
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarEstudiante(@PathVariable Integer id) {
      boolean eliminado = iPruebaEstudianteService.eliminarPruebaEstudiante(id);
      if (eliminado){
          return ResponseEntity.ok().body(MessageUtil.ESTUDIANTE_ELIMINADO_EXITOSAMENTE);
      }else{
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtil.ERROR_AL_ELIMINAR_ESTUDIANTE);
      }
    }
}
