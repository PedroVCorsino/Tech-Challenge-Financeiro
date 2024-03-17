package br.com.grupo27.techchallenge03.external.security;

import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;

import br.com.grupo27.techchallenge03.external.config.aws.CognitoSigningKeyResolver;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String COGNITO_JWKS_URL = "https://cognito-idp.us-east-1.amazonaws.com/us-east-1_PCH3tTb67/.well-known/jwks.json";
    private final JwkProvider jwkProvider;

    public JwtTokenFilter() throws MalformedURLException {
        this.jwkProvider = new JwkProviderBuilder(new URL(COGNITO_JWKS_URL)).build();
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        
        if (requestURI != null && requestURI.startsWith("/public/")) {
            filterChain.doFilter(request, response);
            return;
        }
    
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwtToken = authHeader.substring(7);
            try {
                Claims claims = validateToken(jwtToken);

                String username = (String) claims.get("cognito:username");
                List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
                
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
    
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }
        }
        
        filterChain.doFilter(request, response);
    }

    private Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKeyResolver(new CognitoSigningKeyResolver(jwkProvider))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
