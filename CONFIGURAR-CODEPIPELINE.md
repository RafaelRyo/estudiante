# ⚙️ Configurar CodePipeline para Ejecución Automática

## 🔍 Problema Actual
CodePipeline **NO se ejecuta automáticamente** cuando haces push a master.

## ✅ Solución: Verificar Configuración en AWS

### Opción 1: Verificar y Actualizar CodePipeline (Recomendado)

#### Paso 1: Ir a CodePipeline
1. Abre la **Consola de AWS**
2. Ve a **CodePipeline** (busca "CodePipeline" en la barra de búsqueda)
3. Selecciona tu pipeline

#### Paso 2: Verificar la Configuración de Source
1. Haz clic en **Edit** (botón naranja arriba a la derecha)
2. En la etapa **Source**, haz clic en **Edit stage**
3. Haz clic en el ícono de editar (lápiz) en tu acción de Source

#### Paso 3: Verificar la Detección de Cambios
Deberías ver:
```
Source provider: GitHub (Version 2) ✅
Connection: [tu conexión]
Repository: RafaelRyo/estudiante
Branch: master ✅ MUY IMPORTANTE
```

**Busca la opción:**
```
Change detection options:
○ Start the pipeline on source code change (MANUAL)
● Start the pipeline on source code change (WEBHOOK) ✅ DEBE ESTAR ESTA
```

Si está en "MANUAL", cámbialo a "WEBHOOK" o "Amazon CloudWatch Events".

#### Paso 4: Verificar el Webhook
1. Ve a la sección **Source** de tu pipeline
2. Verifica que tenga un **webhook activo** conectado a GitHub
3. Si no existe, AWS lo creará automáticamente al cambiar a detección automática

### Opción 2: Si Usas GitHub (Version 1)

Si tu Source está configurado con "GitHub (Version 1)":

1. Ve a tu repositorio en GitHub: https://github.com/RafaelRyo/estudiante
2. Ve a **Settings** → **Webhooks**
3. Busca un webhook que apunte a `codepipeline.amazonaws.com`
4. Verifica que esté:
   - ✅ Active (con check verde)
   - ✅ Recent Deliveries mostrando actividad

Si no existe el webhook o está inactivo:
1. Vuelve a AWS CodePipeline
2. Edit → Source stage → Edit action
3. Reconecta con GitHub
4. AWS creará el webhook automáticamente

### Opción 3: Migrar a GitHub (Version 2) - Recomendado

Si estás usando GitHub Version 1, es mejor migrar a Version 2:

1. En AWS CodePipeline, edita tu pipeline
2. En Source stage, cambia el proveedor a **"GitHub (Version 2)"**
3. Crea una nueva conexión usando AWS CodeStar Connections
4. Selecciona tu repositorio: `RafaelRyo/estudiante`
5. Selecciona la rama: `master`
6. Guarda los cambios

GitHub Version 2 es más confiable y usa AWS CodeStar Connections.

## 🔧 Verificación Rápida

### ¿Cómo saber si está configurado correctamente?

1. **Ve a tu CodePipeline**
2. Busca en la parte superior si dice:
   ```
   🔄 Polling for changes (cada X minutos) ❌ MAL
   ```
   O:
   ```
   🔔 Webhook configured ✅ BIEN
   ```

## ⚡ Solución Temporal: Ejecutar Manualmente

Mientras corriges la configuración, puedes ejecutar el pipeline manualmente:

1. Ve a tu pipeline en AWS CodePipeline
2. Haz clic en el botón **"Release change"** (arriba a la derecha)
3. Confirma la ejecución

Esto ejecutará el pipeline inmediatamente con el último commit de master.

## 📋 Checklist de Verificación

- [ ] CodePipeline está apuntando a la rama `master` (no a `main` u otra)
- [ ] La detección de cambios está en "WEBHOOK" o "CloudWatch Events"
- [ ] El webhook en GitHub está activo (si usas GitHub v1)
- [ ] La conexión de CodeStar está activa (si usas GitHub v2)
- [ ] Has guardado todos los cambios en AWS CodePipeline

## 🎯 Después de Corregir la Configuración

1. Haz un cambio pequeño en cualquier archivo (por ejemplo, agrega un comentario)
2. Commit y push:
   ```bash
   git add .
   git commit -m "Test: Verificar trigger automático"
   git push origin master
   ```
3. Ve a AWS CodePipeline en los siguientes 30 segundos
4. Deberías ver que el pipeline empieza a ejecutarse automáticamente

## 🆘 Si Nada Funciona

Si después de verificar todo sigue sin funcionar automáticamente:

1. **Elimina y recrea el pipeline** (última opción)
2. Asegúrate de seleccionar:
   - Source: GitHub Version 2
   - Change detection: Webhook
   - Repository: RafaelRyo/estudiante
   - Branch: master

---

**Por ahora, usa "Release change" manualmente** para probar que el buildspec.yml funciona correctamente. Luego corrige la configuración del webhook para que sea automático.

