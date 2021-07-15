package com.ceiba.combo.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.combo.comando.ComandoCombo;
import com.ceiba.combo.comando.manejador.ManejadorActualizarCombo;
import com.ceiba.combo.comando.manejador.ManejadorCrearCombo;
import com.ceiba.combo.comando.manejador.ManejadorEliminarCombo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/combos")
@Api(tags = { "Controlador comando combo"})
public class ComandoControladorCombo {

    private final ManejadorCrearCombo manejadorCrearCombo;
    private final ManejadorActualizarCombo manejadorActualizarCombo;
    private final ManejadorEliminarCombo manejadorEliminarCombo;

    @Autowired
    public ComandoControladorCombo(ManejadorCrearCombo manejadorCrearCombo, ManejadorActualizarCombo manejadorActualizarCombo, ManejadorEliminarCombo manejadorEliminarCombo) {
        this.manejadorCrearCombo = manejadorCrearCombo;
        this.manejadorActualizarCombo = manejadorActualizarCombo;
        this.manejadorEliminarCombo = manejadorEliminarCombo;
    }
    @PostMapping
    @ApiOperation("Crear combo")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoCombo comandoCombo){
        return manejadorCrearCombo.ejecutar(comandoCombo);
    }
    @DeleteMapping(value="/{id}")
    @ApiOperation("Eliminar combo")
    public void eliminar(@PathVariable Long id) {
        manejadorEliminarCombo.ejecutar(id);
    }

    @PutMapping(value="/{id}")
    @ApiOperation("Actualizar combo")
    public void actualizar(@RequestBody ComandoCombo comandoCombo, @PathVariable Long id) {
        comandoCombo.setId(id);
        manejadorActualizarCombo.ejecutar(comandoCombo);
    }
}

