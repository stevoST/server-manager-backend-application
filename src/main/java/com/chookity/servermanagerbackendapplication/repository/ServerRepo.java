package com.chookity.servermanagerbackendapplication.repository;

import com.chookity.servermanagerbackendapplication.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server, Long> {
    Server findByIpAddress(final String ipAddress);
}
