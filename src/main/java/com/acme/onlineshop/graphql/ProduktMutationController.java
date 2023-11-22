package com.acme.onlineshop.graphql;

import com.acme.onlineshop.service.ProduktWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

/**
 * Eine Controller-Klasse für das Schreiben mit der GraphQL-Schnittstelle und den Typen aus dem GraphQL-Schema.
 *
 * @author <a href="mailto:gale1012@h-ka.de">Leon Gauweiler</a>
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class ProduktMutationController {
    private final ProduktWriteService service;
    private final ProduktInputMapper mapper;

    /**
     * Einen neuen Datensatz für ein Produkt anlegen.
     *
     * @param input Die Eingabedaten für ein neues Produkt
     * @return Die generierte sku für das neue Produkt als Payload
     */
    @MutationMapping
    CreatePayload create(@Argument final ProduktInput input) {
        log.debug("create: input = {}", input);
        final var newProdukt = mapper.toProdukt(input);
        final var sku = service.create(newProdukt).getSku();
        log.debug("create: sku= {}", sku);
        return new CreatePayload(sku);
    }
}
