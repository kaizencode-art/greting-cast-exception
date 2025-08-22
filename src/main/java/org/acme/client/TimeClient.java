package org.acme.client;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.qualifiers.Flavor;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/ip")
@RegisterRestClient(configKey = "time")
@Flavor(pcts = {0.1, 0.2})
public interface TimeClient {

    @GET String ip();
}
