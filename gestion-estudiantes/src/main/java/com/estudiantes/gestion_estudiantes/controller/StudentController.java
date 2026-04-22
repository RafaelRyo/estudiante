package com.estudiantes.gestion_estudiantes.controller;

import com.estudiantes.gestion_estudiantes.models.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudiante")
public class StudentController {

    // @GetMapping
    // public List<Student> obtenerPruebaEstudiantes(){
    //     return iPruebaEstudianteService.searchStudents();
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<Student> obtenerEstudiantePorId(@PathVariable Integer id){
    //     Optional<Student> estudiante = iPruebaEstudianteService.obtenerEstudiantePorId(id);
    //     return estudiante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    // }

    @PostMapping("/registrar")
    public ResponseEntity<?> guardarPruebaEstudiante(@RequestBody Student pruebaEstudiante) {
        String error = validarCamposObligatorios(pruebaEstudiante);
        if (error != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        // Modo prueba: solo confirmamos que el micro recibio los datos.
        return ResponseEntity.ok("Micro recibio los datos correctamente");
    }

    // @PutMapping("/actualizar/{id}")
    // public ResponseEntity<?> actualizarPruebaEstudiante(
    //         @PathVariable Integer id, @RequestBody Student pruebaEstudiante){
    //     Student updateEstudiante = iPruebaEstudianteService.actualizarPruebaEstudiante(id, pruebaEstudiante);
    //     if(updateEstudiante != null){
    //         return ResponseEntity.ok().body(MessageUtil.ESTUDIANTE_ACTUALIZADO_EXITOSAMENTE);
    //     }
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtil.ESTUDIANTE_NO_ENCONTRADO);
    // }

    // @DeleteMapping("/eliminar/{id}")
    // public ResponseEntity<?> eliminarEstudiante(@PathVariable Integer id) {
    //   boolean eliminado = iPruebaEstudianteService.eliminarPruebaEstudiante(id);
    //   if (eliminado){
    //       return ResponseEntity.ok().body(MessageUtil.ESTUDIANTE_ELIMINADO_EXITOSAMENTE);
    //   }else{
    //       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtil.ERROR_AL_ELIMINAR_ESTUDIANTE);
    //   }
    // }

    private String validarCamposObligatorios(Student estudiante) {
        if (estudiante == null) {
            return "El body es obligatorio";
        }
        if (estudiante.getEid() == null) {
            return "El campo 'eid' es obligatorio";
        }
        if (estudiante.getName() == null || estudiante.getName().trim().isEmpty()) {
            return "El campo 'name' es obligatorio";
        }
        if (estudiante.getLastNames() == null || estudiante.getLastNames().trim().isEmpty()) {
            return "El campo 'lastNames' es obligatorio";
        }
        if (estudiante.getDateBirth() == null) {
            return "El campo 'dateBirth' es obligatorio";
        }
        if (estudiante.getGrade() == null || estudiante.getGrade().trim().isEmpty()) {
            return "El campo 'grade' es obligatorio";
        }
        return null;
    }
}
