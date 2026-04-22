# Deployment a AWS con CodePipeline y Elastic Beanstalk

## Cambios Realizados

### 1. buildspec.yml
- **Agregada sección `artifacts`**: Esta es crucial para que CodePipeline pueda pasar el JAR compilado a Elastic Beanstalk.
- **Preparación de artefactos**: Se copia el JAR, Procfile y configuraciones de .ebextensions a un directorio `output` que se usa como base para los artefactos.

### 2. Procfile
- Archivo nuevo que le indica a Elastic Beanstalk cómo ejecutar la aplicación Java.
- Especifica el comando para iniciar la aplicación Spring Boot.

### 3. .ebextensions/java-settings.config
- Configuración específica para el entorno de Elastic Beanstalk.
- Configura el puerto del servidor (5000) como variable de entorno.
- Establece los parámetros de memoria JVM (Xmx y Xms).
- Desactiva el proxy nginx ya que Spring Boot tiene su propio servidor embebido.

## Pasos para Desplegar

1. **Commit y push de los cambios**:
   ```bash
   git add .
   git commit -m "Configurar deployment para AWS CodePipeline y Elastic Beanstalk"
   git push origin main
   ```

2. **Verificar CodePipeline**:
   - Ve a la consola de AWS CodePipeline
   - Verifica que el pipeline se ejecute automáticamente después del push
   - Revisa los logs de CodeBuild para asegurarte de que la compilación sea exitosa

3. **Verificar Elastic Beanstalk**:
   - Ve a la consola de Elastic Beanstalk
   - Verifica que el entorno esté en estado "Ok" (verde)
   - Revisa los logs si hay algún error

4. **Probar la aplicación**:
   - Accede a la URL de tu entorno de Elastic Beanstalk
   - Prueba los endpoints de tu API

## Problemas Comunes y Soluciones

### El pipeline no se activa
- Verifica que CodePipeline esté correctamente conectado a tu repositorio
- Asegúrate de hacer push a la rama correcta (main o la que hayas configurado)

### El build falla
- Revisa los logs de CodeBuild en la consola de AWS
- Verifica que el runtime de Java sea compatible (corretto17)

### El deployment falla
- Verifica que el nombre del JAR en el Procfile coincida con el generado en build.gradle
- Revisa los logs de Elastic Beanstalk en: Logs > Request Logs > Last 100 Lines

### La aplicación no responde
- Verifica que el puerto configurado sea el correcto (5000)
- Asegúrate de que los security groups permitan tráfico en el puerto 80
- Revisa los logs de la aplicación en Elastic Beanstalk

## Verificación de la Configuración

La aplicación Spring Boot está configurada para:
- Ejecutarse en el puerto 5000 (configurable con variable de entorno SERVER_PORT o PORT)
- Usar Java 17
- No requiere base de datos (MySQL está comentado en application.properties)

## Siguiente Paso

Cuando hagas cambios en el código:
1. Haz commit de tus cambios
2. Push a tu repositorio
3. CodePipeline detectará automáticamente el cambio
4. CodeBuild compilará el proyecto
5. El artefacto se desplegará automáticamente en Elastic Beanstalk

¡El pipeline ahora debería funcionar correctamente!

