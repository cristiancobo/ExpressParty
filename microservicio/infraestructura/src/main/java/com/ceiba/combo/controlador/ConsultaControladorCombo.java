package com.ceiba.combo.controlador;

import com.ceiba.combo.consulta.ManejadorListarCombos;
import com.ceiba.combo.modelo.dto.DtoCombo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/combos")
@Api(tags={"Controlador consulta combo"})
public class ConsultaControladorCombo {

    private final ManejadorListarCombos manejadorListarCombos;

    public ConsultaControladorCombo(ManejadorListarCombos manejadorListarCombos) {
        this.manejadorListarCombos = manejadorListarCombos;
    }

    @GetMapping
    @ApiOperation("Listar combos")
    public List<DtoCombo> listar(){
        return this.manejadorListarCombos.ejecutar();
    }
}