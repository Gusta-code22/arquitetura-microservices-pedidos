package github.io.Gusta_code22.controller;

import github.io.Gusta_code22.dto.PrecoDTO;
import github.io.Gusta_code22.service.PrecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/preco")
@RequiredArgsConstructor
public class PrecoController {


    private final PrecoService service;

    //GET /preco/{valor}/{moeda}
    @GetMapping(value = "/{valor}/{moeda}")
    public PrecoDTO getPreco(
            @PathVariable("valor") BigDecimal valor,
            @PathVariable("moeda") String moeda){

        return service.calcularPrecoFinal(valor, moeda);
    }
}
