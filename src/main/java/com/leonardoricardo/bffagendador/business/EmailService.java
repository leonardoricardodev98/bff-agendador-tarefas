package com.leonardoricardo.bffagendador.business;

import com.leonardoricardo.bffagendador.business.dto.out.TarefasDTOResponse;
import com.leonardoricardo.bffagendador.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviaEmail(TarefasDTOResponse dto) {
        emailClient.enviarEmail(dto);
    }
}