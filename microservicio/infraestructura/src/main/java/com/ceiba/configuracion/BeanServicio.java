package com.ceiba.configuracion;

import com.ceiba.combo.puerto.repositorio.RepositorioCombo;
import com.ceiba.combo.servicio.ServicioActualizarCombo;
import com.ceiba.combo.servicio.ServicioCrearCombo;
import com.ceiba.combo.servicio.ServicioEliminarCombo;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.ServicioActualizarReserva;
import com.ceiba.reserva.servicio.ServicioCrearReserva;
import com.ceiba.reserva.servicio.ServicioEliminarReserva;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearCombo servicioCrearCombo(RepositorioCombo repositorioCombo){
        return new ServicioCrearCombo(repositorioCombo);
    }
    @Bean
    public ServicioActualizarCombo servicioActualizarCombo(RepositorioCombo repositorioCombo){
        return new ServicioActualizarCombo(repositorioCombo);
    }
    @Bean
    public ServicioEliminarCombo servicioEliminarCombo(RepositorioCombo repositorioCombo){
        return new ServicioEliminarCombo(repositorioCombo);
    }

    @Bean
    public ServicioCrearReserva servicioCrearReserva(RepositorioReserva repositorioReserva, RepositorioCombo repositorioCombo){
        return new ServicioCrearReserva(repositorioReserva, repositorioCombo);
    }
    @Bean
    public ServicioActualizarReserva servicioActualizarReserva(RepositorioReserva repositorioReserva){
        return new ServicioActualizarReserva(repositorioReserva);
    }
    @Bean
    public ServicioEliminarReserva servicioEliminarReserva(RepositorioReserva repositorioReserva){
        return new ServicioEliminarReserva(repositorioReserva);
    }

}
