package com.franco.insalud.web_insalud.controller;

import com.franco.insalud.web_insalud.entity.Atencion;
import com.franco.insalud.web_insalud.entity.Usuario;
import com.franco.insalud.web_insalud.entity.Paciente;
import com.franco.insalud.web_insalud.service.AtencionService;
import com.franco.insalud.web_insalud.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/atenciones")
public class AtencionController {

    @Autowired
    private AtencionService atencionService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> listarAtenciones() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String rol = auth.getAuthorities().iterator().next().getAuthority();
        
        if ("ADMIN".equals(rol) || "MEDICO".equals(rol)) {
            List<Atencion> atenciones = atencionService.listar();
            return ResponseEntity.ok(atenciones);
        }
        
        Map<String, String> error = new HashMap<>();
        error.put("error", "Acceso denegado. Se requiere rol ADMIN o MEDICO");
        return ResponseEntity.status(403).body(error);
    }

  
    @GetMapping("/mias")
    public ResponseEntity<?> listarMisAtenciones() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        String rol = auth.getAuthorities().iterator().next().getAuthority();

        if ("PACIENTE".equals(rol)) {
            try {
               
                Optional<Usuario> usuarioOpt = usuarioService.findByUsuario(username);
                if (usuarioOpt.isPresent()) {
                    Usuario usuario = usuarioOpt.get();
                    
                    if (usuario.getPersona() != null && usuario.getPersona().getPaciente() != null) {
                        Paciente paciente = usuario.getPersona().getPaciente();
                        Long pacienteId = paciente.getId();

                        List<Atencion> misAtenciones = atencionService.findByPacienteId(pacienteId);

                        for (Atencion atencion : misAtenciones) {
                            if (!atencion.getPaciente().getId().equals(pacienteId)) {
                                Map<String, String> error = new HashMap<>();
                                error.put("error", "Error de seguridad: Atención no pertenece al paciente");
                                return ResponseEntity.status(403).body(error);
                            }
                        }
                        
                        return ResponseEntity.ok(misAtenciones);
                    } else {
                        Map<String, String> error = new HashMap<>();
                        error.put("error", "Usuario no tiene un paciente asociado");
                        return ResponseEntity.status(400).body(error);
                    }
                } else {
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "Usuario no encontrado");
                    return ResponseEntity.status(404).body(error);
                }
            } catch (Exception e) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Error al obtener las atenciones: " + e.getMessage());
                return ResponseEntity.status(500).body(error);
            }
        }

        Map<String, String> error = new HashMap<>();
        error.put("error", "Acceso denegado. Se requiere rol PACIENTE");
        return ResponseEntity.status(403).body(error);
    }

    @PostMapping
    public ResponseEntity<?> crearAtencion(@RequestBody Atencion atencion) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String rol = auth.getAuthorities().iterator().next().getAuthority();
        
        if ("ADMIN".equals(rol) || "MEDICO".equals(rol)) {
            try {
                atencion.setFecha(LocalDate.now());
                if (atencion.getEstado() == null) {
                    atencion.setEstado("ACTIVA");
                }
                Atencion nuevaAtencion = atencionService.guardar(atencion);
                
                Map<String, String> response = new HashMap<>();
                response.put("mensaje", "Atención creada exitosamente");
                response.put("id", nuevaAtencion.getId().toString());
                
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Error al crear la atención: " + e.getMessage());
                return ResponseEntity.badRequest().body(error);
            }
        }
        
        Map<String, String> error = new HashMap<>();
        error.put("error", "Acceso denegado. Se requiere rol ADMIN o MEDICO");
        return ResponseEntity.status(403).body(error);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAtencion(@PathVariable Long id, @RequestBody Atencion atencion) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String rol = auth.getAuthorities().iterator().next().getAuthority();
        
        if ("ADMIN".equals(rol) || "MEDICO".equals(rol)) {
            Optional<Atencion> atencionExistente = atencionService.buscarPorId(id);
            
            if (atencionExistente.isPresent()) {
                atencion.setId(id);
                Atencion atencionActualizada = atencionService.guardar(atencion);
                
                Map<String, String> response = new HashMap<>();
                response.put("mensaje", "Atención actualizada exitosamente");
                response.put("id", atencionActualizada.getId().toString());
                
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Atención no encontrada con ID: " + id);
                return ResponseEntity.status(404).body(error);
            }
        }
        
        Map<String, String> error = new HashMap<>();
        error.put("error", "Acceso denegado. Se requiere rol ADMIN o MEDICO");
        return ResponseEntity.status(403).body(error);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAtencion(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String rol = auth.getAuthorities().iterator().next().getAuthority();
        
        if ("ADMIN".equals(rol)) {
            Optional<Atencion> atencion = atencionService.buscarPorId(id);
            
            if (atencion.isPresent()) {
                atencionService.eliminar(id);
                
                Map<String, String> response = new HashMap<>();
                response.put("mensaje", "Atención eliminada exitosamente");
                response.put("id", id.toString());
                
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Atención no encontrada con ID: " + id);
                return ResponseEntity.status(404).body(error);
            }
        }
        
        Map<String, String> error = new HashMap<>();
        error.put("error", "Acceso denegado. Se requiere rol ADMIN");
        return ResponseEntity.status(403).body(error);
    }

    @GetMapping("/buscar-por-fecha")
    public ResponseEntity<?> buscarPorFecha(@RequestParam LocalDate fecha) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String rol = auth.getAuthorities().iterator().next().getAuthority();
        
        if ("ADMIN".equals(rol) || "MEDICO".equals(rol)) {
            List<Atencion> atenciones = atencionService.buscarPorFecha(fecha);
            return ResponseEntity.ok(atenciones);
        }
        
        Map<String, String> error = new HashMap<>();
        error.put("error", "Acceso denegado. Se requiere rol ADMIN o MEDICO");
        return ResponseEntity.status(403).body(error);
    }
    
     
}