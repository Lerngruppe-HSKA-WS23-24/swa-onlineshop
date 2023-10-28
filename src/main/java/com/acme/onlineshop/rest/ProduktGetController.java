package com.acme.onlineshop.rest;

import com.acme.onlineshop.service.ProduktReadService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.acme.onlineshop.rest.ProduktGetController.REST_PATH;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller um die Get Requests für die Produkte zu bedienen.
 *
 * @author <a href="mailto:leon.gauweiler@gmail.com">Leon Gauweiler</a>
 */
@RestController
@RequestMapping(REST_PATH)
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("TrailingComment")
public class ProduktGetController {
    /**
     * Basispfad für die REST-Schnittstelle.
     */
    public static final String REST_PATH = "/rest"; //NOSONAR

    /**
     * Muster für eine UUID. [\dA-Fa-f]{8}{8}-([\dA-Fa-f]{8}{4}-){3}[\dA-Fa-f]{8}{12} enthält eine "capturing group"
     * und ist nicht zulässig.
     */
    public static final String ID_PATTERN =
        "[\\dA-Fa-f]{8}-[\\dA-Fa-f]{4}-[\\dA-Fa-f]{4}-[\\dA-Fa-f]{4}-[\\dA-Fa-f]{12}";

    /**
     * Pfad, um Nachnamen abzufragen.
     */
    private final ProduktReadService service;
    private final UriHelper uriHelper;

    // https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-ann-methods
    // https://localhost:8080/swagger-ui.html
    /**
     * Suche anhand der Produkt-sku als Pfad-Parameter.
     *
     * @param sku sku des zu suchenden Produkts
     * @param request Das Request-Objekt, um Links für HATEOAS zu erstellen.
     * @return Gefundener Kunde mit Atom-Links.
     */
    @GetMapping(path = "{sku:" + ID_PATTERN + "}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBySku(@PathVariable final UUID sku, final HttpServletRequest request) {
        log.debug("getBySku: sku={}, Thread={}", sku, Thread.currentThread().getName());

        // Geschaeftslogik bzw. Anwendungskern
        final var produkt = service.findBySku(sku);

        if (produkt == null) {
            return ResponseEntity.notFound().build();
        }

        // HATEOAS
        // evtl. Forwarding von einem API-Gateway
        final var baseUri = uriHelper.getBaseUri(request).toString();
        final var idUri = STR."\{baseUri}/\{produkt.getSku()}";
        final var selfLink = Link.of(idUri);
        final var listLink = Link.of(baseUri, LinkRelation.of("list"));
        final var addLink = Link.of(baseUri, LinkRelation.of("add"));
        final var updateLink = Link.of(idUri, LinkRelation.of("update"));
        final var removeLink = Link.of(idUri, LinkRelation.of("remove"));

        final Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("produkt", produkt);
        responseBody.put("links", Arrays.asList(selfLink, listLink, addLink, updateLink, removeLink));

        log.debug("produkt: {}", responseBody);
        return ResponseEntity.ok(responseBody);
    }
}
