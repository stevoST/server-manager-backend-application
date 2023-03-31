package com.chookity.servermanagerbackendapplication.service.impl;

import com.chookity.servermanagerbackendapplication.enumeration.Status;
import com.chookity.servermanagerbackendapplication.model.Server;
import com.chookity.servermanagerbackendapplication.repository.ServerRepository;
import com.chookity.servermanagerbackendapplication.service.ServerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;

    @Override
    public Server create(final Server server) {
        //TODO add MDC
        log.info("Saving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }

    @Override
    public Server ping(final String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        final Server server = serverRepository.findByIpAddress(ipAddress);
        final InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(final int limit) {
        log.info("Fetching all servers");
        return serverRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server get(final Long id) {
        log.info("Fetching server by ID: {}", id);
        //TODO rework if nothing is found
        return serverRepository.findById(id).get();
    }

    @Override
    public Server update(final Server server) {
        log.info("Updating server: {}", server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(final Long id) {
        //TODO test unknown id
        log.info("Deleting server by ID: {}", id);
        serverRepository.deleteById(id);
        return Boolean.TRUE;
    }

    private String setServerImageUrl() {
        final String[] imageNames = {"server1.png", "server2.png", "server3.png", "server4.png"};
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/server/image/" + imageNames[new Random().nextInt(4)])
                .toUriString();
    }
}
