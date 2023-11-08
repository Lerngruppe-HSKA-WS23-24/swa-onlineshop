package com.acme.onlineshop.rest;

import com.acme.onlineshop.service.ConstraintViolationsException;
import com.acme.onlineshop.service.NameExistsException;
import com.acme.onlineshop.service.ProduktWriteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.UUID;

import static com.acme.onlineshop.rest.ProduktGetController.REST_PATH;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;
import static com.acme.onlineshop.rest.ProduktGetController.ID_PATTERN;


/**
 * Controller um die Post Requests für die Produkte zu bedienen.
 *
 * @author <a href="mailto:leon.gauweiler@gmail.com">Leon Gauweiler</a>
 */
@RestController
@RequestMapping(REST_PATH)
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("TrailingComment")
public class ProduktWriteController {
    private static final String PROBLEM_PATH = "/problem/";


    private final ProduktWriteService service;
    private final UriHelper uriHelper;
    private final ProduktMapper mapper;


    /**
     * Finde all Produkte.
     *
     * @return Alle Produkte.
     */
    @PostMapping(path = "", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addProdukt(
        @RequestBody() final ProduktDTO produktDTO,
        final HttpServletRequest request
    ) {
        final var produkt = service.create(mapper.toProdukt(produktDTO));
        final var baseUri = uriHelper.getBaseUri(request).toString();
        final var location = URI.create(STR."\{baseUri}/\{produkt.getSku()}");
        return created(location).build();
    }

    /**
     * Einen vorhandenen Produkt-Datensatz überschreiben.
     *
     * @param sku ID des zu aktualisierenden Produkte.
     * @param produktDTO Das Produktobjekt aus dem eingegangenen Request-Body.
     */
    @PutMapping(path = "{sku:" + ID_PATTERN + "}", consumes = APPLICATION_JSON_VALUE)
    void put(@PathVariable final UUID sku, @RequestBody final ProduktDTO produktDTO) {
        log.debug("put: sku={}, {}", sku, produktDTO);
        final var produktInput = mapper.toProdukt(produktDTO);
        service.update(produktInput, sku);
    }

    @ExceptionHandler
    ProblemDetail onConstraintViolations(
        final ConstraintViolationsException ex,
        final HttpServletRequest request
    ) {
        log.debug("onConstraintViolations: {}", ex.getMessage());

        final var produktViolations = ex.getViolations()
            .stream()
            .map(violation -> STR."\{violation.getPropertyPath()}: " +
                STR."\{violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName()} " +
                violation.getMessage())
            .toList();
        log.trace("onConstraintViolations: {}", produktViolations);
        final String detail;
        if (produktViolations.isEmpty()) {
            detail = "N/A";
        } else {
            final var violationsStr = produktViolations.toString();
            detail = violationsStr.substring(1, violationsStr.length() - 2);
        }

        final var problemDetail = ProblemDetail.forStatusAndDetail(UNPROCESSABLE_ENTITY, detail);
        problemDetail.setType(URI.create(STR."\{PROBLEM_PATH}\{ProblemType.CONSTRAINTS.getValue()}"));
        problemDetail.setInstance(URI.create(request.getRequestURL().toString()));

        return problemDetail;
    }

    @ExceptionHandler
    ProblemDetail onNameExists(final NameExistsException ex, final HttpServletRequest request) {
        log.debug("onNameExists: {}", ex.getMessage());
        final var problemDetail = ProblemDetail.forStatusAndDetail(UNPROCESSABLE_ENTITY, ex.getMessage());
        problemDetail.setType(URI.create(STR."\{PROBLEM_PATH}\{ProblemType.CONSTRAINTS.getValue()}"));
        problemDetail.setInstance(URI.create(request.getRequestURL().toString()));
        return problemDetail;
    }
}
