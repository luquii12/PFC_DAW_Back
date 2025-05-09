package com.chatterbox.api_rest.repository;

import com.chatterbox.api_rest.dto.grupo.GrupoChatDto;
import com.chatterbox.api_rest.dto.grupo.GrupoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class GruposRepository {
    private final JdbcClient jdbcClient;

    public Optional<GrupoDto> findGrupoById(Long idGrupo) {
        return jdbcClient.sql("SELECT * FROM grupos WHERE id_grupo = ?")
                .param(1, idGrupo)
                .query(GrupoDto.class)
                .optional();
    }

    public List<GrupoChatDto> findChatsByGrupoIdOrderByFechaCreacion(Long idGrupo) {
        return jdbcClient.sql("SELECT id_chat, nombre_chat, DATE_FORMAT(fecha_creacion, '%Y-%m-%d %H:%i:%s') AS fecha_creacion FROM chats WHERE id_grupo = ? ORDER BY fecha_creacion")
                .param(1, idGrupo)
                .query(GrupoChatDto.class)
                .list();
    }
}
