package com.ies.module.admin.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestToken = request.getHeader("Authorization");
        //Bearer 2352523sdgsg

        String username = null;
        String token = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token

        if (requestToken !=null && requestToken.startsWith("Bearer")){

            token = requestToken.substring(7);

            try {
                username = this.jwtTokenHelper.getUsernameFromToken(token);
            }
            catch (IllegalArgumentException e){
                System.out.println("Unable to get Jwt token!");
            }
            catch (ExpiredJwtException e){
                System.out.println("Jwt token has expired!");
            }
            catch (MalformedJwtException e){
                System.out.println("Invalid Jwt!");
            }
        }else
        {
            System.out.println("Jwt token does not begin with Bearer!");
        }

        // Once we get the token validate it.
        if (username !=null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (this.jwtTokenHelper.validateToken(token, userDetails)){

                //sab kuch sahi chal raha hai
                //authentication karna hai
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else
            {
                System.out.println("Invalid Jwt token!");
            }

        }else
        {
            System.out.println("username is null or context is null!");
        }

        filterChain.doFilter(request,response);


    }
}
