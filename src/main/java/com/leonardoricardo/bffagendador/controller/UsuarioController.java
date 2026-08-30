package com.leonardoricardo.bffagendador.controller;


import com.leonardoricardo.bffagendador.business.UsuarioService;
import com.leonardoricardo.bffagendador.business.dto.in.EnderecoDTORequest;
import com.leonardoricardo.bffagendador.business.dto.in.LoginRequestDTO;
import com.leonardoricardo.bffagendador.business.dto.in.TelefoneDTORequest;
import com.leonardoricardo.bffagendador.business.dto.in.UsuarioDTORequest;
import com.leonardoricardo.bffagendador.business.dto.out.EnderecoDTOResponse;
import com.leonardoricardo.bffagendador.business.dto.out.TelefoneDTOResponse;
import com.leonardoricardo.bffagendador.business.dto.out.UsuarioDTOResponse;
import com.leonardoricardo.bffagendador.business.dto.out.ViaCepDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadastro e login de usuários")

public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "Salvar Usuarios", description = "Cria um novo usuario")
    @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "409", description = "Usuario já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")

    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuarios", description = "Login do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")

    public String login(@RequestBody LoginRequestDTO usuarioDTO){
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de Usuarios por email", description = "buscar dados do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")

    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta Usuarios por id", description = "Deleta usuario")
    @ApiResponse(responseCode = "200", description = "Usuario deletado")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")

    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name = "Authorization", required = false) String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados de Usuarios", description = "Atualizar dados de usuario")
    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")

    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                                                   @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza endereço de usuario", description = "Atualiza endereco de usuario")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")

    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza telefone de usuario", description = "Atualiza telefone de usuario")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")

    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva endereço de usuario", description = "Salva endereco de usuario")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")

    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva telefone de usuario", description = "Salva telefone de usuario")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")

    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }

    @GetMapping("/endereco/{cep}")
    @Operation(summary = "Busca endereco pelo cep", description = "Busca dados de endereco recebendo um cep")
    @ApiResponse(responseCode = "200", description = "Dados de endereco retornado com sucesso")
    @ApiResponse(responseCode = "400", description = "Cep invalido")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")

    public ResponseEntity<ViaCepDTOResponse> buscarEndereco(@PathVariable ("cep") String cep){
        return ResponseEntity.ok(usuarioService.buscarEnderecoPorCep(cep));
    }

}
