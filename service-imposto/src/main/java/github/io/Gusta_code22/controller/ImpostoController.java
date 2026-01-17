package github.io.Gusta_code22.controller;


import github.io.Gusta_code22.dto.ImpostoDTO;
import github.io.Gusta_code22.service.ImpostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/service-imposto")

// GET /imposto/{valor}/{moeda}
public class ImpostoController {

    @Autowired
    private ImpostoService service;

    @GetMapping(value = "/{valor}/{moeda}")
    public ImpostoDTO getImposto(
            @PathVariable("valor") BigDecimal valor,
            @PathVariable("moeda") String moeda
            ){

        return service.calcularImposto(valor, moeda);

    }
}
