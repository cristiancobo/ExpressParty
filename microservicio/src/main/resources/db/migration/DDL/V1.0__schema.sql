create table if not exists combo (
 id int(11) not null auto_increment,

 nombre varchar(11) not null,
 precio decimal(45,5) not null,
 primary key (id)

);

create table if not exists reserva (
 id int(11) not null auto_increment,
 combo_id int(11),
 precio decimal(45,5) not null,
 fecha_creacion datetime null,
 fecha_reserva datetime null,
 fecha_expiracion datetime null,
 nombre_persona varchar(45) not null,
 id_persona varchar(45) not null,
 telefono_persona varchar(45) not null,
 direccion_persona varchar (45) not null,
 primary key (id),
 INDEX (combo_id),
  FOREIGN KEY (combo_id)
      REFERENCES combo(id)
      ON DELETE CASCADE
);