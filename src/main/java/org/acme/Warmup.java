package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.client.TimeClient;
import org.acme.qualifiers.Flavor;

@ApplicationScoped
public class Warmup {
    void onStart(@jakarta.enterprise.event.Observes io.quarkus.runtime.StartupEvent ev) {
        var handle = io.quarkus.arc.Arc.container().instance(
                org.acme.client.TimeClient.class,
                new org.acme.qualifiers.Flavor.Literal(0.95),
                org.eclipse.microprofile.rest.client.inject.RestClient.LITERAL // required
        );
        handle.get().ip();
    }

}