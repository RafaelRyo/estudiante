package com.estudiantes.gestion_estudiantes.infrastructure.adapter.in;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.estudiantes.gestion_estudiantes.application.dto.StudentRequestDTO;
import com.estudiantes.gestion_estudiantes.domain.port.in.StudentMessageInputPort;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class SqsStudentMessageListener {
    private final StudentMessageInputPort studentMessageInputPort;
    private final ObjectMapper objectMapper;
    @SqsListener("${aws.sqs.queue-name}")
    public void onMessage(String rawMessage) {
        log.info("[SQS] Mensaje recibido: {}", rawMessage);
        try {
            StudentRequestDTO dto = objectMapper.readValue(rawMessage, StudentRequestDTO.class);
            studentMessageInputPort.processStudentMessage(dto);
        } catch (IllegalArgumentException e) {
            log.error("[SQS] Mensaje invalido, enviando a DLQ: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("[SQS] Error procesando mensaje, enviando a DLQ: {}", e.getMessage(), e);
            throw new RuntimeException("Error procesando mensaje SQS", e);
        }
    }
}
