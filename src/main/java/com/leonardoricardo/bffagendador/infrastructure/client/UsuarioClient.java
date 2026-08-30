package com.leonardoricardo.bffagendador.infrastructure.client;

import com.leonardoricardo.bffagendador.business.dto.in.EnderecoDTORequest;
import com.leonardoricardo.bffagendador.business.dto.in.LoginRequestDTO;
import com.leonardoricardo.bffagendador.business.dto.in.TelefoneDTORequest;
import com.leonardoricardo.bffagendador.business.dto.in.UsuarioDTORequest;
import com.leonardoricardo.bffagendador.business.dto.out.EnderecoDTOResponse;
import com.leonardoricardo.bffagendador.business.dto.out.TelefoneDTOResponse;
import com.leonardoricardo.bffagendador.business.dto.out.UsuarioDTOResponse;
import com.leonardoricardo.bffagendador.business.dto.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")

public interface UsuarioClient {


    @GetMapping
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader(name = "Authorization", required = false) String token);

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);


    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO usuarioDTO);


    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                            @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader(name = "Authorization", required = false) String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestHeader(name = "Authorization", required = false) String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping("/endereco/{cep}")
    ViaCepDTOResponse buscarDadosCep(@PathVariable("cep") String cep );
}
