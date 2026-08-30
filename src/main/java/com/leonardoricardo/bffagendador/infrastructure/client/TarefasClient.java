package com.leonardoricardo.bffagendador.infrastructure.client;

import com.leonardoricardo.bffagendador.business.dto.in.TarefasDTORequest;
import com.leonardoricardo.bffagendador.business.dto.out.TarefasDTOResponse;
import com.leonardoricardo.bffagendador.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")

public interface TarefasClient {


    @PostMapping
    TarefasDTOResponse gravarTarefas(@RequestBody TarefasDTORequest dto,
                                     @RequestHeader(name = "Authorization", required = false) String token);


    @GetMapping("/eventos")

    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping
    List<TarefasDTOResponse> buscaTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token);

    @DeleteMapping
    void deletaTarefaPorId(@RequestParam("id") String id,
                           @RequestHeader(name = "Authorization", required = false) String token);

    @PatchMapping
    TarefasDTOResponse alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum statusNotificacaoEnum,
                                               @RequestParam("id") String id,
                                               @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping
    TarefasDTOResponse updateTarefas(
            @RequestBody TarefasDTORequest dto,
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token);
}
