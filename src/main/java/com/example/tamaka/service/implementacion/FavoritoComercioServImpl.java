package com.example.tamaka.service.implementacion;

import com.example.tamaka.dto.response.ComercioResponse;
import com.example.tamaka.entity.Comercio;
import com.example.tamaka.entity.FavoritoComercio;
import com.example.tamaka.entity.Usuario;
import com.example.tamaka.mapper.ComercioMapper;
import com.example.tamaka.repository.FavoritoComercioRepository;
import com.example.tamaka.repository.UsuarioRepository;
import com.example.tamaka.service.IComercioServ;
import com.example.tamaka.service.IFavoritoComercioServ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritoComercioServImpl implements IFavoritoComercioServ {

    private final FavoritoComercioRepository favoritoComercioRepository;
    private final UsuarioRepository usuarioRepository;
    private final IComercioServ comercioService;
    private final ComercioMapper comercioMapper;

    @Override
    @Transactional
    public ComercioResponse agregar(Long usuarioId, Long comercioId) {

        if (favoritoComercioRepository.existsByUsuarioIdAndComercioId(usuarioId, comercioId)) {
            throw new RuntimeException("Este comercio ya está en tus favoritos");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Comercio comercio = comercioService.obtenerEntidad(comercioId);

        if (!comercio.isActivo()) {
            throw new RuntimeException("El comercio no está disponible");
        }

        FavoritoComercio favorito = FavoritoComercio.builder()
                .usuario(usuario)
                .comercio(comercio)
                .build();

        favoritoComercioRepository.save(favorito);

        return comercioMapper.toResponse(comercio);
    }

    @Override
    public List<ComercioResponse> listar(Long usuarioId) {
        return favoritoComercioRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(FavoritoComercio::getComercio)
                .map(comercioMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void eliminar(Long usuarioId, Long comercioId) {
        FavoritoComercio favorito = favoritoComercioRepository
                .findByUsuarioIdAndComercioId(usuarioId, comercioId)
                .orElseThrow(() -> new RuntimeException("Este comercio no está en tus favoritos"));

        favoritoComercioRepository.delete(favorito);
    }

    @Override
    public boolean esFavorito(Long usuarioId, Long comercioId) {
        return favoritoComercioRepository.existsByUsuarioIdAndComercioId(usuarioId, comercioId);
    }
}