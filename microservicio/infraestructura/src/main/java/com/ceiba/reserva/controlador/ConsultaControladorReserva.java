package com.ceiba.reserva.controlador;

import com.ceiba.reserva.consulta.ManejadorListarReservas;
import com.ceiba.reserva.consulta.ManejadorObtenerReserva;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@Api(tags={"Controlador consulta reserva"})
public class ConsultaControladorReserva {

    private final ManejadorListarReservas manejadorListarReservas;
    private final ManejadorObtenerReserva manejadorObtenerReserva;

    public ConsultaControladorReserva(ManejadorListarReservas manejadorListarReservas, ManejadorObtenerReserva manejadorObtenerReserva) {
        this.manejadorListarReservas = manejadorListarReservas;
        this.manejadorObtenerReserva = manejadorObtenerReserva;
    }

    @GetMapping
    @ApiOperation("Listar reservas")
    public List<DtoReserva> listar(){
        return this.manejadorListarReservas.ejecutar();
    }

    @GetMapping(value="/{id}")
    @ApiOperation("Obtener reserva")
    public DtoReserva obtenerReservaPorId(@PathVariable Long id){
        return this.manejadorObtenerReserva.ejecutar(id);
    }
}