package com.estudiantes.gestion_estudiantes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.estudiantes.gestion_estudiantes.application.dto.StudentRequestDTO;
import com.estudiantes.gestion_estudiantes.domain.port.in.StudentMessageInputPort;
import com.estudiantes.gestion_estudiantes.infrastructure.adapter.in.SqsStudentMessageListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class SqsStudentMessageListenerTest {
    @Mock
    private StudentMessageInputPort studentMessageInputPort;
    @Spy
    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    @InjectMocks
    private SqsStudentMessageListener listener;
    private String validJson;
    @BeforeEach
    void setUp() {
        validJson = "{\"name\":\"Juan\",\"lastNames\":\"Perez\",\"dateBirth\":\"2000-05-15\",\"grade\":\"10\"}";
    }
    @Test
    void shouldProcessValidMessage() {
        listener.onMessage(validJson);
        ArgumentCaptor<StudentRequestDTO> captor = ArgumentCaptor.forClass(StudentRequestDTO.class);
        verify(studentMessageInputPort, times(1)).processStudentMessage(captor.capture());
        StudentRequestDTO captured = captor.getValue();
        assertEquals("Juan", captured.getName());
        assertEquals("Perez", captured.getLastNames());
        assertEquals(LocalDate.of(2000, 5, 15), captured.getDateBirth());
        assertEquals("10", captured.getGrade());
    }
    @Test
    void shouldThrowRuntimeExceptionOnMalformedJson() {
        String badJson = "{invalid-json}";
        assertThrows(RuntimeException.class, () -> listener.onMessage(badJson));
        verify(studentMessageInputPort, never()).processStudentMessage(any());
    }
    @Test
    void shouldPropagateExceptionWhenUseCaseFails() {
        doThrow(new RuntimeException("DB error")).when(studentMessageInputPort).processStudentMessage(any());
        assertThrows(RuntimeException.class, () -> listener.onMessage(validJson));
    }
}
