package br.com.vivo.application.config;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class UrlConfig {

    // a anotacao injeta o valor da propriedade passada no name na variavel
    @ConfigProperty(name = "api-pokemon-base-url/mp-rest/url")
    String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }
}
