/**
 * Essa Classe nao se torna nescessario já que adicionamos essa mesma funcionalidade no YAML
 * podemos entao remover esse Hard-code
 */

/*
package github.io.Gusta_code22.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){

        return builder.routes()

                .route(p -> p.path("/service-produto/**").uri("lb://service-produto"))
                .route(p -> p.path("/service-preco/**").uri("lb://service-preco"))
                .route(p -> p.path("/service-imposto/**").uri("lb://service-imposto"))
                .build();
    }
}
*/

