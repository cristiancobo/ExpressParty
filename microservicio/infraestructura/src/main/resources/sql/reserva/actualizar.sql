update reserva
set combo_id = :comboId,
	precio = :precioCombo,
	fecha_creacion = :fechaCreacionReserva,
	fecha_reserva = :fechaReservacion,
	fecha_expiracion = :fechaExpiracion,
	nombre_persona = :nombrePersonaReserva,
	id_persona = :idPersonaReserva,
	telefono_persona = :telefonoPersonReserva,
	direccion_persona = :direccionPersonaReserva
where id = :id