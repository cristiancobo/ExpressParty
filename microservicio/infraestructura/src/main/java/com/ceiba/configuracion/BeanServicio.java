package com.ceiba.configuracion;

import com.ceiba.combo.puerto.repositorio.RepositorioCombo;
import com.ceiba.combo.servicio.ServicioActualizarCombo;
import com.ceiba.combo.servicio.ServicioCrearCombo;
import com.ceiba.combo.servicio.ServicioEliminarCombo;
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

}
