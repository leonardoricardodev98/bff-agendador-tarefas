package com.leonardoricardo.bffagendador.business;

import com.leonardoricardo.bffagendador.business.dto.in.TarefasDTORequest;
import com.leonardoricardo.bffagendador.business.dto.out.TarefasDTOResponse;
import com.leonardoricardo.bffagendador.business.enums.StatusNotificacaoEnum;
import com.leonardoricardo.bffagendador.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;

    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest dto) {
        return tarefasClient.gravarTarefas(dto, token);
    }

    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo(
            LocalDateTime dataInicial,
            LocalDateTime dataFinal,
            String token) {

        return tarefasClient
                .buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token)
                .getBody();
    }

    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token) {

        return tarefasClient.buscaTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {

        tarefasClient.deletaTarefaPorId(id, token);

    }

    public TarefasDTOResponse alteraStatus(StatusNotificacaoEnum status, String id, String token) {
        return tarefasClient.alteraStatusNotificacao(status, id, token);
    }

    public TarefasDTOResponse updateTarefas(
            TarefasDTORequest dto,
            String id,
            String token) {

        return tarefasClient.updateTarefas(dto, id, token);
    }
}