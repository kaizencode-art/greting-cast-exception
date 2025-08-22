package org.acme.rest;

import io.micrometer.core.annotation.Timed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.client.TimeClient;
import org.acme.qualifiers.Flavor;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/greet")
@ApplicationScoped
public class GreetingResource {
    // Ensure a REST client with @Timed annotation is part of the application
    @Inject @RestClient @Flavor(pcts = {0.1, 0.2})
    TimeClient timeClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws NoSuchMethodException {
//        return greetingService.greet("Quarkus");
        // Prouver que l’attribut de l’annotation est un tableau primitif
        var m = io.micrometer.core.annotation.Timed.class.getMethod("percentiles");
        boolean isPrimitiveArray = m.getReturnType().isArray()
                && m.getReturnType().getComponentType().isPrimitive();

        return timeClient.ip();
    }
}
