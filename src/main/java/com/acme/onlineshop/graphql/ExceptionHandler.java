package com.acme.onlineshop.graphql;

import com.acme.onlineshop.service.NameExistsException;
import com.acme.onlineshop.service.NotFoundException;
import graphql.GraphQLError;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import static org.springframework.graphql.execution.ErrorType.NOT_FOUND;

/**
 * Abbildung von Exceptions auf GraphQLError.
 *
 * @author <a href="mailto:gale1012@h-ka.de">Leon Gauweiler</a>
 */
@ControllerAdvice
public class ExceptionHandler {
    @GraphQlExceptionHandler
    GraphQLError onNotFound(final NotFoundException ex) {
        final var sku = ex.getSku();
        final var message = sku == null
            ? STR."Kein Produkt gefunden: suchkriterien=\{ex.getSuchkriterien()}"
            : STR."Kein Produkt mit der SKU \{sku} gefunden";
        return GraphQLError.newError()
            .errorType(NOT_FOUND)
            .message(message)
            .build();
    }

    @GraphQlExceptionHandler
    GraphQLError onNameExist(final NameExistsException ex) {
        final var name = ex.getName();
        final var message = STR."Der Name \{name} existiert bereits";
        return GraphQLError.newError()
            .errorType(NOT_FOUND)
            .message(message)
            .build();
    }
}
