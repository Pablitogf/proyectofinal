package co.edu.uniquindio.proyectofinal.application.services.unit;

import co.edu.uniquindio.proyectofinal.infrastructure.rest.dto.LoginRequest;
import co.edu.uniquindio.proyectofinal.infrastructure.rest.dto.TokenResponse;
import co.edu.uniquindio.proyectofinal.infrastructure.security.JwtTokenProvider;
import co.edu.uniquindio.proyectofinal.infrastructure.security.services.SecurityServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.util.ReflectionTestUtils;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SecurityServiceImplTest {

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private SecurityServiceImpl securityService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(securityService, "expiryInMinutes", 30L);
    }

    @Test
    void testLoginExitoso() {

        Authentication dummyAuth =
                new UsernamePasswordAuthenticationToken(
                        "admin@test.com",
                        "admin123",
                        List.of(() -> "ADMIN")
                );

        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenReturn(dummyAuth);

        when(jwtTokenProvider.generateTokenAsString(
                anyString(),
                anyList(),
                any(Instant.class),
                any(Instant.class)
        )).thenReturn("fake-token");

        LoginRequest request = new LoginRequest(
                "admin@test.com",
                "admin123"
        );

        TokenResponse response = securityService.login(request);

        assertNotNull(response);
        assertEquals("Bearer", response.type());
        assertEquals("fake-token", response.token());

        verify(authenticationManager, times(1))
                .authenticate(any(Authentication.class));

        verify(jwtTokenProvider, times(1))
                .generateTokenAsString(anyString(), anyList(), any(), any());
    }
}