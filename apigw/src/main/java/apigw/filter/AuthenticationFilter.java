package apigw.filter;

import apigw.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.*;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator validator;

    @Autowired
    JwtUtil jwtUtil;

    public AuthenticationFilter() {


        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            System.out.println("-------------------------");
            System.out.println(validator.isSecured.test(exchange.getRequest()));



            if (validator.isSecured.test(exchange.getRequest())) {
                //header content token or not


                System.out.println(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION));
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {

                    return onError(exchange, HttpStatus.UNAUTHORIZED);


                }


                String token = exchange.getRequest().getHeaders().get(org.springframework.http.HttpHeaders.AUTHORIZATION).get(0);
                if (token != null && token.startsWith("Bearer ")) {
                    token = token.substring(7);
                }
                try {
                    //   template.getForObject("http://USERS//user//validatetoken?token"+authHeader,String.class);

                    jwtUtil.validateToken(token);


                } catch (Exception e) {
                    return onError(exchange, HttpStatus.UNAUTHORIZED);
                }
                Claims claims = jwtUtil.getClaimsFromToken(token);

                String userRole = (String) claims.get("role");
                System.out.println(userRole);
                String requestedResource = exchange.getRequest().getPath().toString();
                System.out.println(requestedResource);
                Map<String, List<String>> resourceRoles = new HashMap<>();

                resourceRoles.put("/user/admin/", Arrays.asList("ADMIN"));

                resourceRoles.put("/user/rh", Arrays.asList("RH"));
                resourceRoles.put("/user/employer", Arrays.asList("EMPLOYER"));
                resourceRoles.put("/salaire",Arrays.asList("RH"));
                resourceRoles.put("/conge/leave-requests/Rh",Arrays.asList("RH"));
                resourceRoles.put("/conge/leave-requests/employer",Arrays.asList("EMPLOYER"));
                resourceRoles.put("/api/v1/formations", Arrays.asList("RH"));

                Set<String> keys=resourceRoles.keySet();
                System.out.println(keys);
                List<String> allowedRoles=null;
                for (String key:keys){
                    System.out.println(key);
                    if (requestedResource.startsWith(key)){

                         allowedRoles = resourceRoles.get(key);


                    }
                }
                System.out.println(allowedRoles);




                if (allowedRoles != null && allowedRoles.contains(userRole)) {

                    return chain.filter(exchange);
                }
                else {
                    return onError(exchange, HttpStatus.UNAUTHORIZED);


                }
            }
            System.out.println("yesy");

            return chain.filter(exchange);
        }));
    }

    public static class Config {

    }
    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
}
