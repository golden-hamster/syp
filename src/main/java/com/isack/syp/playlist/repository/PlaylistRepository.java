package com.isack.syp.playlist.repository;

import com.isack.syp.playlist.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    Optional<Playlist> findByApiId(String apiId);
}
