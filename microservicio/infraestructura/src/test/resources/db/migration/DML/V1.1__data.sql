insert into combo (nombre,precio)values('combo 1',100000.0);
insert into combo (nombre,precio)values('combo 2',200000.0);
insert into combo (nombre,precio)values('combo 3',300000.0);

insert into reserva (combo_id,precio,fecha_creacion,fecha_reserva,fecha_expiracion,nombre_persona,id_persona,telefono_persona,direccion_persona)values(1,100000.0,now(),now(),now(),'carla','12345678','3200340','cra. 20');
insert into reserva (combo_id,precio,fecha_creacion,fecha_reserva,fecha_expiracion,nombre_persona,id_persona,telefono_persona,direccion_persona)values(2,200000.0,now(),now(),now(),'alberto','98989667','3200340','cra. 21');
insert into reserva (combo_id,precio,fecha_creacion,fecha_reserva,fecha_expiracion,nombre_persona,id_persona,telefono_persona,direccion_persona)values(3,300000.0,now(),now(),now(),'mario','78898989','3200340','cra. 23');
