//package veniamin.backend.atelie.filter.jwt;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import veniamin.backend.atelie.constant.PathConstants;
//import veniamin.backend.atelie.entity.User;
//import veniamin.backend.atelie.exception.AuthorizeException;
//import veniamin.backend.atelie.exception.errors.AuthorizedError;
//import veniamin.backend.atelie.service.UserService;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtTokenFilter extends OncePerRequestFilter {
//
//    private final UserService userService;
//
//    private final JwtUtils jwtUtils;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token = request.getHeader("Authorization");
//        if (!request.getRequestURI().startsWith("/public") && token == null) {
//            throw new AuthorizeException(AuthorizedError.NOT_CORRECT_TOKEN);
//        }
//        //Если это публичный эндпоинт и токен не равен нулю, то сетать authentication или же если это не публичный эндпоинт, то тоже сетать эндпоинт;
//        if ((request.getRequestURI().startsWith("/public") && token != null) || !(request.getRequestURI().startsWith("/public"))) {
//
//            token = token.replace("Bearer", "");
//            jwtUtils.validateToken(token);
//            String typeToken = jwtUtils.getTypeTokenFromToken(token);
//            if (typeToken.equals("refresh")) {
//                throw new AuthorizeException(AuthorizedError.NOT_CORRECT_TOKEN);
//            }
//            String email = jwtUtils.getUserEmailFromToken(token);
//            User user = (User) userService.loadUserByUsername(email);
//            if (!user.getIsEmailVerificated()) {
//                throw new AccessDeniedException("Пользователь не верифицирован");
//            }
//            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(email, null, user.getAuthorities()));
//
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        return request.getRequestURI().startsWith(PathConstants.AUTHORIZE_CONTROLLER_PATH)
//                || request.getRequestURI().startsWith("/css")
//                || request.getRequestURI().startsWith("/js")
//                || request.getRequestURI().startsWith("/images")
//                || request.getRequestURI().equals("/favicon.ico");
//    }
//}
