package alisson.springSecurityJWT.security;

import io.jsonwebtoken.*;
import net.bytebuddy.asm.Advice;

import java.util.List;
import java.util.stream.Collectors;

public class JWTCreator {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "authorities";

    private static List<String> checkRoles(List<String> roles){
        return roles.stream().map(s -> "ROLE_".concat(s.replaceAll("ROLE_",""))).collect(Collectors.toList());
    }

    public static String create(String prefix, String key, JWTObject jwtObject){
        String token = Jwts.builder().setSubject(jwtObject.getSubject()).setIssuedAt(jwtObject.getIssuedAt()).setExpiration(jwtObject.getExpiration())
                .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles())).signWith(SignatureAlgorithm.HS512, key).compact();
        return prefix + " " + token;

    }

    public static JWTObject create(String token, String prefix, String key)
        throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException{
        JWTObject jwtObject = new JWTObject();
        token = token.replace(prefix, "");
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        jwtObject.setSubject(claims.getSubject());
        jwtObject.setExpiration(claims.getExpiration());
        jwtObject.setIssuedAt(claims.getIssuedAt());
        jwtObject.setRoles((List<String>) claims.get(ROLES_AUTHORITIES));

        return jwtObject;
    }



}
