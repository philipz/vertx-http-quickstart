import io.vertx.axle.core.eventbus.EventBus;
import io.vertx.axle.core.eventbus.Message;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.concurrent.CompletionStage;

@Path("/hello")
public class EventResource {

    @Inject EventBus bus;

    @GET
    @Path("/async/{name}")
    public CompletionStage<String> hello(@PathParam("name") String name) {
        return bus.<String>send("greeting", name)
                .thenApply(Message::body);
    }
}