package com.mypet.core.login.adapter.in.controller;

import com.mypet.core.login.adapter.in.dto.LoginRegisterRequest;
import com.mypet.core.login.adapter.in.dto.LoginRequest;
import com.mypet.core.login.adapter.in.dto.LoginResponse;
import com.mypet.core.login.port.in.AuthLoginUseCase;
import com.mypet.core.login.port.in.CadastrarLoginUseCase;
import com.mypet.core.login.port.in.AlterarSenhaUseCase;
import com.mypet.core.login.port.in.DeletarLoginUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {
        "http://45.93.100.30:4200",
        "http://192.168.15.2:4200",
        "http://192.168.15.200:4200",
        "http://localhost:4200"
})
public class LoginController {

    private final AuthLoginUseCase authLoginUseCase;
    private final CadastrarLoginUseCase cadastrarLoginUseCase;
    private final AlterarSenhaUseCase alterarSenhaUseCase;
    private final DeletarLoginUseCase deletarLoginUseCase;

    public LoginController(AuthLoginUseCase authLoginUseCase,
                           CadastrarLoginUseCase cadastrarLoginUseCase,
                           AlterarSenhaUseCase alterarSenhaUseCase,
                           DeletarLoginUseCase deletarLoginUseCase) {
        this.authLoginUseCase = authLoginUseCase;
        this.cadastrarLoginUseCase = cadastrarLoginUseCase;
        this.alterarSenhaUseCase = alterarSenhaUseCase;
        this.deletarLoginUseCase = deletarLoginUseCase;
    }

    // =========================
    // REGISTER
    // =========================
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRegisterRequest request) {
        try {
            String response = cadastrarLoginUseCase.cadastrar(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno no servidor");
        }
    }

    // =========================
    // LOGIN
    // =========================
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authLoginUseCase.autenticar(request);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // =========================
    // UPDATE PASSWORD
    // =========================
    @PutMapping("/update/{username}")
    public ResponseEntity<String> updatePassword(
            @PathVariable String username,
            @RequestBody LoginRequest request) {
        try {
            String response = alterarSenhaUseCase.alterarSenha(username, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno no servidor");
        }
    }

    // =========================
    // DELETE USER
    // =========================
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        try {
            String response = deletarLoginUseCase.deletar(username);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno no servidor");
        }
    }
}