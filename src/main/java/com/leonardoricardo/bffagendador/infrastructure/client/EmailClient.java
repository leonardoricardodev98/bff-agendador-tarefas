package com.leonardoricardo.bffagendador.infrastructure.client;

import com.leonardoricardo.bffagendador.business.dto.in.EnderecoDTORequest;
import com.leonardoricardo.bffagendador.business.dto.in.LoginRequestDTO;
import com.leonardoricardo.bffagendador.business.dto.in.TelefoneDTORequest;
import com.leonardoricardo.bffagendador.business.dto.in.UsuarioDTORequest;
import com.leonardoricardo.bffagendador.business.dto.out.EnderecoDTOResponse;
import com.leonardoricardo.bffagendador.business.dto.out.TarefasDTOResponse;
import com.leonardoricardo.bffagendador.business.dto.out.TelefoneDTOResponse;
import com.leonardoricardo.bffagendador.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "notificacao", url = "${notificacao.url}")

public interface EmailClient {


    void enviarEmail(@RequestBody TarefasDTOResponse dto);
}
