package com.chookity.servermanagerbackendapplication.service;

import com.chookity.servermanagerbackendapplication.model.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

public interface ServerService {
    Server create(final Server server);
    Server ping(final String ipAddress) throws IOException;
    Collection<Server> list(final int limit);
    Server get(final Long id);
    Server update(final Server server);
    Boolean delete(final Long id);
}
