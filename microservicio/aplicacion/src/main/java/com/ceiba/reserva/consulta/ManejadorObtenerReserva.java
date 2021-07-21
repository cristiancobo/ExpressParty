package com.ceiba.reserva.consulta;

import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import org.springframework.stereotype.Component;

@Component
public class ManejadorObtenerReserva {

    public final DaoReserva daoReserva;

    public ManejadorObtenerReserva(DaoReserva daoReserva) {
        this.daoReserva = daoReserva;
    }
    public DtoReserva ejecutar(Long id){
        return this.daoReserva.obtenerReservaPorId(id);
    }
}
