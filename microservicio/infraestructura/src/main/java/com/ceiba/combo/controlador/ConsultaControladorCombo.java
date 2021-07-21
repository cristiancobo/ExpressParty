package com.ceiba.combo.controlador;

import com.ceiba.combo.consulta.ManejadorListarCombos;
import com.ceiba.combo.consulta.ManejadorObtenerCombo;
import com.ceiba.combo.modelo.dto.DtoCombo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/combos")
@Api(tags={"Controlador consulta combo"})
public class ConsultaControladorCombo {

    private final ManejadorListarCombos manejadorListarCombos;
    private final ManejadorObtenerCombo manejadorObtenerCombo;

    public ConsultaControladorCombo(ManejadorListarCombos manejadorListarCombos, ManejadorObtenerCombo manejadorObtenerCombo) {
        this.manejadorListarCombos = manejadorListarCombos;
        this.manejadorObtenerCombo = manejadorObtenerCombo;
    }

    @GetMapping
    @ApiOperation("Listar combos")
    public List<DtoCombo> listar(){
        return this.manejadorListarCombos.ejecutar();
    }

    @GetMapping(value="/{id}")
    @ApiOperation("Obtener combo")
    public DtoCombo obtenerComboPorId(@PathVariable Long id){
        return this.manejadorObtenerCombo.ejecutar(id);
    }

}