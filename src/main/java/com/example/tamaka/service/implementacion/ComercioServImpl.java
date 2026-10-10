package com.example.tamaka.service.implementacion;


import com.example.tamaka.dto.request.ComercioRequest;
import com.example.tamaka.dto.response.ComercioResponse;
import com.example.tamaka.entity.Comercio;
import com.example.tamaka.entity.Usuario;
import com.example.tamaka.mapper.ComercioMapper;
import com.example.tamaka.repository.ComercioRepository;
import com.example.tamaka.repository.UsuarioRepository;
import com.example.tamaka.service.IComercioServ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ComercioServImpl implements IComercioServ {

    private final ComercioRepository comercioRepository;
    private final UsuarioRepository usuarioRepository;
    private final ComercioMapper comercioMapper;

    @Override
    @Transactional
    public ComercioResponse crear(Long operadorId, ComercioRequest request) {

        if (request.getRnt() == null || request.getRnt().isBlank()) {
            throw new BusinessException("El RNT es obligatorio para registrar un comercio");
        }
        if (comercioRepository.existsByRnt(request.getRnt())) {
            throw new BusinessException("Ya existe un comercio con ese RNT");
        }

        Usuario operador = usuarioRepository.findById(operadorId)
                .orElseThrow(() -> new BusinessException("Usuario no encontrado"));

        if (!operador.getRoles().contains(Usuario.Rol.OPERADOR)) {
            operador.getRoles().add(Usuario.Rol.OPERADOR);
            usuarioRepository.save(operador);
        }

        Comercio comercio = Comercio.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .rnt(request.getRnt())
                .activo(true)
                .operador(operador)
                .build();

        return comercioMapper.toResponse(comercioRepository.save(comercio));
    }

    @Override
    public ComercioResponse obtener(Long id) {
        return comercioMapper.toResponse(obtenerEntidad(id));
    }

    @Override
    public Comercio obtenerEntidad(Long id) {
        return comercioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Comercio no encontrado"));
    }
}