# A.4 — Documento de Reglas de Negocio

| Acción regulada | Condición que debe cumplirse | RF asociado | Ubicación en código |
|------|------|------|------|
| Registrar solicitud | La descripción y el solicitante son obligatorios | RF-01 | Solicitud.Builder.build() |
| Crear solicitud | Toda solicitud inicia en estado CREADA | RF-01 | Solicitud constructor |
| Clasificar solicitud | No se puede clasificar sin prioridad ni tipo | RF-02 | Solicitud.clasificar() |
| Clasificar solicitud | Solo se puede clasificar si el estado es CREADA | RF-02 | Solicitud.clasificar() |
| Asignar responsable | Solo se puede asignar si está CLASIFICADA o ASIGNADA | RF-04 | Solicitud.asignarResponsable() |
| Atender solicitud | Solo se puede atender si está ASIGNADA | RF-04 | Solicitud.atender() |
| Cerrar solicitud | Solo se puede cerrar si está ATENDIDA | RF-08 | Solicitud.cerrar() |
| Modificar solicitud | Una solicitud cerrada no puede modificarse | RF-08 | Solicitud.validarNoCerrada() |
| Registrar acciones | Toda acción debe registrarse en el historial | RF-06 | Solicitud.registrarHistorial() |
| Validar email | El email debe tener formato válido | RF-13 | Email |
