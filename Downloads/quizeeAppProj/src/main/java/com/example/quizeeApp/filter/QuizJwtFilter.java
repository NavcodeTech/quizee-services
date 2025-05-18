//package com.example.quizeeApp.filter;
//
//import java.io.IOException;
//import java.security.PublicKey;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//
//@Component
//public class QuizJwtFilter extends OncePerRequestFilter{
//
//	private PublicKey publicKey;
//
//    @Autowired
//    public void setPublicKey(PublicKey publicKey) {
//        this.publicKey = publicKey;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);  // Extract JWT token
//            try {
//                // Validate and parse the token using the public key
//                Claims claims = Jwts.parserBuilder()
//                        .setSigningKey(publicKey)
//                        .build()
//                        .parseClaimsJws(token)
//                        .getBody();
//
//                // Extract user info from token claims
//                String userId = claims.getSubject(); // The userId is typically the subject
//                String username = (String) claims.get("username");
//
//                // Set user info as request attributes
//                request.setAttribute("userId", userId);
//                request.setAttribute("username", username);
//            } catch (JwtException e) {
//                // Invalid JWT token
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
//                return;
//            }
//        }
//
//        // Proceed with the filter chain
//        filterChain.doFilter(request, response);
//    } 
//}
