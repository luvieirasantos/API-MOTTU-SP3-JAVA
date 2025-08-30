package com.fiap.mottu.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * SERVIÇO: JwtService
 * 
 * Este serviço é responsável por toda a lógica relacionada aos tokens JWT
 * (JSON Web Tokens). Ele gera, valida e extrai informações dos tokens
 * usados para autenticação no sistema.
 * 
 * FUNÇÃO: Gerenciamento completo de tokens JWT
 * SEGURANÇA: Criptografia e validação de tokens
 * TECNOLOGIA: Biblioteca JJWT (Java JWT)
 * ALGORITMO: HS256 (HMAC SHA-256)
 */
@Service
public class JwtService {

    /**
     * CHAVE SECRETA PARA ASSINATURA
     * 
     * FUNÇÃO: Chave usada para assinar e verificar tokens
     * ORIGEM: application.yml (jwt.secret)
     * SEGURANÇA: Deve ser mantida em segredo
     * 
     * IMPORTANTE: 
     * - Em produção, usar chave forte e única
     * - Nunca commitar no código
     * - Usar variáveis de ambiente
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * TEMPO DE EXPIRAÇÃO DO TOKEN
     * 
     * FUNÇÃO: Define quanto tempo o token é válido
     * ORIGEM: application.yml (jwt.expiration)
     * UNIDADE: Milissegundos
     * 
     * EXEMPLO: 5184000000 = 60 dias
     */
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * MÉTODO: getSigningKey()
     * 
     * FUNÇÃO: Cria chave de assinatura a partir do secret
     * RETORNO: SecretKey para assinar tokens
     * ALGORITMO: HMAC SHA-256
     * 
     * IMPORTANTE: 
     * - Converte string secret em chave criptográfica
     * - Usado para assinar e verificar tokens
     * - Chave deve ter tamanho adequado para o algoritmo
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * MÉTODO: extractUsername(String token)
     * 
     * FUNÇÃO: Extrai o username (email) do token JWT
     * RETORNO: String com o email do usuário
     * USO: Identificar usuário a partir do token
     * 
     * IMPORTANTE: 
     * - Usa extractClaim com Claims::getSubject
     * - Subject do JWT contém o email do usuário
     * - Usado para autenticação em requisições
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * MÉTODO: extractExpiration(String token)
     * 
     * FUNÇÃO: Extrai a data de expiração do token
     * RETORNO: Date com momento de expiração
     * USO: Verificar se token ainda é válido
     * 
     * IMPORTANTE: 
     * - Usa extractClaim com Claims::getExpiration
     * - Usado para validação de expiração
     * - Compara com data atual
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * MÉTODO: extractClaim(String token, Function<Claims, T> claimsResolver)
     * 
     * FUNÇÃO: Extrai qualquer claim específico do token
     * RETORNO: Tipo genérico T (String, Date, etc.)
     * USO: Extrair informações específicas do token
     * 
     * IMPORTANTE: 
     * - Método genérico para extrair qualquer claim
     * - Usa Function para resolver qual claim extrair
     * - Reutilizado por outros métodos de extração
     * 
     * EXEMPLO: extractClaim(token, Claims::getSubject) para username
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * MÉTODO: extractAllClaims(String token)
     * 
     * FUNÇÃO: Extrai todos os claims do token JWT
     * RETORNO: Claims com todas as informações do token
     * USO: Base para extrair claims específicos
     * 
     * IMPORTANTE: 
     * - Verifica assinatura do token com chave secreta
     * - Retorna payload completo do token
     * - Método privado usado internamente
     * 
     * SEGURANÇA: Verifica se token foi assinado corretamente
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * MÉTODO: isTokenExpired(String token)
     * 
     * FUNÇÃO: Verifica se o token expirou
     * RETORNO: boolean (true se expirou, false se válido)
     * USO: Validação de expiração antes de aceitar token
     * 
     * IMPORTANTE: 
     * - Compara data de expiração com data atual
     * - Token expirado não deve ser aceito
     * - Usado para validação de segurança
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * MÉTODO: generateToken(UserDetails userDetails)
     * 
     * FUNÇÃO: Gera novo token JWT para usuário
     * RETORNO: String com token JWT assinado
     * USO: Após login bem-sucedido
     * 
     * IMPORTANTE: 
     * - Cria token com claims básicos
     * - Usa email como subject (username)
     * - Token é assinado com chave secreta
     * 
     * PARÂMETROS:
     * - userDetails: Informações do usuário autenticado
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * MÉTODO: createToken(Map<String, Object> claims, String subject)
     * 
     * FUNÇÃO: Cria token JWT com claims e subject específicos
     * RETORNO: String com token JWT completo
     * USO: Geração de tokens customizados
     * 
     * IMPORTANTE: 
     * - Método privado usado por generateToken
     * - Define momento de criação e expiração
     * - Assina com algoritmo HS256
     * 
     * CLAIMS INCLUÍDOS:
     * - subject: Email do usuário
     * - issuedAt: Momento de criação
     * - expiration: Momento de expiração
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * MÉTODO: validateToken(String token, UserDetails userDetails)
     * 
     * FUNÇÃO: Valida se token é válido para usuário específico
     * RETORNO: boolean (true se válido, false se inválido)
     * USO: Validação de token em requisições autenticadas
     * 
     * VALIDAÇÕES REALIZADAS:
     * - Token não expirou
     * - Username do token corresponde ao usuário
     * - Token foi assinado corretamente
     * 
     * IMPORTANTE: 
     * - Método principal para validação de tokens
     * - Usado pelo filtro de autenticação
     * - Garante segurança das requisições
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
