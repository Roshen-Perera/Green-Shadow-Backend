package lk.ijse.greenshadowbackend.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JWTService {
    public String extractUserName(String token);
    public Claims extractAllClaims(String token);
    public <T> T extractClaim(String token, Function<Claims,T> claimResolve);
    public String generateToken(
            Map<String,Object> extractClaims,
            UserDetails userDetails
    );
    public boolean isTokenValid(String token, UserDetails userDetails );
    public String generateToken(UserDetails userDetails);

}
