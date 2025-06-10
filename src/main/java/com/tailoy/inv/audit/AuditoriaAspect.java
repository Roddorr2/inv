package com.tailoy.inv.audit;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.tailoy.inv.dto.AuditDescripcion;
import com.tailoy.inv.model.HistorialAccion;
import com.tailoy.inv.model.Usuario;
import com.tailoy.inv.repository.HistorialAccionRepository;
import com.tailoy.inv.repository.UsuarioRepository;

@Aspect
@Component
public class AuditoriaAspect {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private HistorialAccionRepository historialRepo;

    @Around("@annotation(auditable)")
    public Object auditar(ProceedingJoinPoint joinPoint, Auditable auditable) throws Throwable {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Usuario usuario = usuarioRepo.findByCorreo(username)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        Object result = joinPoint.proceed();

        Object[] args = joinPoint.getArgs();
        String detalles = construirDescripcion(args);

        HistorialAccion historial = new HistorialAccion();
        historial.setFecha(LocalDateTime.now());
        historial.setTipoAccion(auditable.tipo());
        historial.setModulo(auditable.modulo());
        historial.setDescripcion(auditable.accion() + " - " + detalles);
        historial.setUsuario(usuario);

        historialRepo.save(historial);
        return result;
    }

    private String construirDescripcion(Object[] args) {
        if (args.length == 0) return "Sin detalles.";

        Object arg = args[0];
        if (arg instanceof AuditDescripcion) {
            return ((AuditDescripcion) arg).getDescripcionParaHistorial();
        }

        return Arrays.toString(args);
    }
}
