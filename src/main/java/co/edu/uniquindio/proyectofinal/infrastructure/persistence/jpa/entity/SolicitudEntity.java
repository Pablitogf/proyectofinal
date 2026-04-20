package co.edu.uniquindio.proyectofinal.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes")
@Getter
@Setter
@NoArgsConstructor
public class SolicitudEntity {

    @Id
    private String id;

    private String codigo;
    private String descripcion;

    private String estado;

    private String solicitanteId;
    private String responsableId;

    private String prioridad;
    private String tipo;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private LocalDateTime fechaCierre;
}