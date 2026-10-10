package com.example.tamaka.service.implementacion;

import com.example.tamaka.dto.response.AtractivoResponse;
import com.example.tamaka.entity.Atractivo;
import com.example.tamaka.entity.FavoritoAtractivo;
import com.example.tamaka.entity.Usuario;
import com.example.tamaka.mapper.AtractivoMapper;
import com.example.tamaka.repository.AtractivoRepository;
import com.example.tamaka.repository.FavoritoAtractivoRepository;
import com.example.tamaka.repository.UsuarioRepository;
import com.example.tamaka.service.IFavoritoAtractivoServ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritoAtractivoServImpl implements IFavoritoAtractivoServ {

    private final FavoritoAtractivoRepository favoritoAtractivoRepository;
    private final UsuarioRepository usuarioRepository;
    private final AtractivoRepository atractivoRepository;
    private final AtractivoMapper atractivoMapper;

    @Override
    @Transactional
    public AtractivoResponse agregar(Long usuarioId, Long atractivoId) {

        if (favoritoAtractivoRepository.existsByUsuarioIdAndAtractivoId(usuarioId, atractivoId)) {
            throw new RuntimeException("Este atractivo ya está en tus favoritos");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Atractivo atractivo = atractivoRepository.findById(atractivoId)
                .orElseThrow(() -> new RuntimeException("Atractivo no encontrado"));

        if (!atractivo.isActivo()) {
            throw new RuntimeException("El atractivo no está disponible");
        }

        FavoritoAtractivo favorito = FavoritoAtractivo.builder()
                .usuario(usuario)
                .atractivo(atractivo)
                .build();

        favoritoAtractivoRepository.save(favorito);

        return atractivoMapper.toResponse(atractivo);
    }

    @Override
    public List<AtractivoResponse> listar(Long usuarioId) {
        return favoritoAtractivoRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(FavoritoAtractivo::getAtractivo)
                .map(atractivoMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void eliminar(Long usuarioId, Long atractivoId) {
        FavoritoAtractivo favorito = favoritoAtractivoRepository
                .findByUsuarioIdAndAtractivoId(usuarioId, atractivoId)
                .orElseThrow(() -> new RuntimeException("Este atractivo no está en tus favoritos"));

        favoritoAtractivoRepository.delete(favorito);
    }

    @Override
    public boolean esFavorito(Long usuarioId, Long atractivoId) {
        return favoritoAtractivoRepository.existsByUsuarioIdAndAtractivoId(usuarioId, atractivoId);
    }
}