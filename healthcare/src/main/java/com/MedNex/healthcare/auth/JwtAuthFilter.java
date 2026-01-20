package com.MedNex.healthcare.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.MedNex.healthcare.config.CustomUserDetailsService;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {// The hierarchy of the type JwtAuthFilter is inconsistent

	private final JwtService jwtService;
	private final CustomUserDetailsService userDetailsService;

	public JwtAuthFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, // Keys cannot be resolved
			HttpServletResponse response, // Keys cannot be resolved
			FilterChain filterChain) throws ServletException, IOException {// Keys cannot be resolved
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String username;

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		jwt = authHeader.substring(7);
		username = jwtService.extractUsername(jwt);

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if (jwtService.isTokenValid(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
