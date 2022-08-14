package com.fluid.authentication.jwt;

import com.fluid.authentication.model.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JWTTokenService {
    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(User user) {
        Date expire = new Date(new Date().getTime() + 10 * 60 * 1000);
        List<String> authorities = user.getAuthorities().stream()
                .map(e-> e.getName().name()).toList();
        return Jwts.builder()
                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .claim("authorities", authorities)
                .setIssuedAt(new Date())
                .setExpiration(expire)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public String loginUserId(String jwt) {
        return parseClaimsFromJWT(jwt).get("id").toString();
    }

    public String extractUsername(String jwt) {
        if (jwt == null) return null;
        return parseClaimsFromJWT(jwt).get("name").toString();
    }

    public String extractEmail(String jwt) {
        if (jwt == null) return null;
        return parseClaimsFromJWT(jwt).get("email").toString();
    }

    public List<String> extractAuthorities(String jwt) {
        if (jwt == null) return new ArrayList<>();
        return (List<String>) parseClaimsFromJWT(jwt).get("authorities");
    }

    private Claims parseClaimsFromJWT(String jwt) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(jwt).getBody();
    }
}
