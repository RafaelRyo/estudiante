# Prueba de tu API en AWS

## ✅ Tu aplicación está desplegada correctamente en:
```
http://gestion-estudiantes-dev-us-east-1.elasticbeanstalk.com
```

## 📋 Endpoint Disponible:

### POST /api/estudiante/registrar

**URL completa:**
```
http://gestion-estudiantes-dev-us-east-1.elasticbeanstalk.com/api/estudiante/registrar
```

## 🧪 Prueba con PowerShell:

```powershell
$body = @{
    eid = 1
    name = "Juan"
    lastNames = "Pérez García"
    dateBirth = "2000-01-15"
    grade = "10mo"
} | ConvertTo-Json

Invoke-WebRequest -Uri "http://gestion-estudiantes-dev-us-east-1.elasticbeanstalk.com/api/estudiante/registrar" -Method POST -Body $body -ContentType "application/json"
```

## 🧪 Prueba con cURL:

```bash
curl -X POST http://gestion-estudiantes-dev-us-east-1.elasticbeanstalk.com/api/estudiante/registrar \
  -H "Content-Type: application/json" \
  -d '{
    "eid": 1,
    "name": "Juan",
    "lastNames": "Pérez García",
    "dateBirth": "2000-01-15",
    "grade": "10mo"
  }'
```

## 🧪 Prueba con Thunder Client / Postman:

**Método:** POST  
**URL:** `http://gestion-estudiantes-dev-us-east-1.elasticbeanstalk.com/api/estudiante/registrar`  
**Headers:**
```
Content-Type: application/json
```

**Body (JSON):**
```json
{
    "eid": 1,
    "name": "Juan",
    "lastNames": "Pérez García",
    "dateBirth": "2000-01-15",
    "grade": "10mo"
}
```

**Respuesta esperada:**
```
"Micro recibio los datos correctamente"
```

## ✅ Si funciona esto:

¡Tu deployment está 100% exitoso! El pipeline está funcionando correctamente.

## 🔄 Para futuros cambios:

Ahora cuando hagas cambios a tu código:
1. Haz commit y push a master
2. CodePipeline debería ejecutarse automáticamente
3. Si no se ejecuta automáticamente, usa "Release change" manualmente
4. Espera 5-7 minutos
5. Tu aplicación se actualizará en la URL de Elastic Beanstalk

---

**¿Puedes probar el endpoint ahora y confirmar que funciona?**

