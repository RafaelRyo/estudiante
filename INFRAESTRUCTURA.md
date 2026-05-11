# Infraestructura: Prometheus y Grafana

## 1. Métricas con Prometheus + Grafana

### Endpoints de Actuator disponibles
| Endpoint | URL |
|---|---|
| Health | `GET /actuator/health` |
| Info | `GET /actuator/info` |
| Métricas (Prometheus) | `GET /actuator/prometheus` |
| Métricas generales | `GET /actuator/metrics` |

### Configuración de scraping en Prometheus

Apunta Prometheus al microservicio según el entorno:

| Escenario | `targets` en prometheus.yml |
|---|---|
| Local (sin Docker) | `localhost:8080` |
| Local con Docker | `host.docker.internal:8080` |
| AWS (misma VPC) | `IP-privada:8080` |

```yaml
scrape_configs:
  - job_name: 'gestion-estudiantes'
    metrics_path: '/actuator/prometheus'
    scrape_interval: 10s
    static_configs:
      - targets: ['<HOST>:8080']
        labels:
          application: 'Sistema de Gestion Estudiantil'
```

### Levantar el stack de monitoreo localmente

```bash
# Desde la raíz del proyecto (carpeta estudiante/)
docker-compose up -d
```

| Servicio | URL | Credenciales |
|---|---|---|
| Prometheus | http://localhost:9090 | — |
| Grafana | http://localhost:3000 | admin / admin123 |

### Configurar Grafana (primera vez)
1. Entra a http://localhost:3000 → el datasource de Prometheus ya viene preconfigurado.
2. Importa el dashboard **Spring Boot 3.x / Micrometer** con ID `19004` desde Grafana.com.
3. Selecciona el datasource `Prometheus` → **Import**.

### Despliegue en AWS (EC2 / ECS)
1. Copia `docker-compose.yml`, `prometheus.yml` y la carpeta `grafana/` al servidor.
2. En `prometheus.yml` reemplaza el host por la IP privada del microservicio.
3. Abre los puertos `9090` y `3000` en el Security Group **solo para IPs de administración**.
4. Para producción se recomienda **Amazon Managed Grafana (AMG)** + **Amazon Managed Prometheus (AMP)**.

---

## 2. Arquitectura resultante

> El API Gateway está gestionado por **Terraform** (repositorio de infraestructura).

```
Cliente
  │
  ▼  x-api-key header
AWS API Gateway  ← gestionado por Terraform
  │
  ▼
Microservicio Spring Boot (:8080)
  │  /api/students/**
  │  /actuator/prometheus  ◄──── Prometheus (:9090) scrape cada 10s
  │                                    │
  └────────────────────────────── Grafana (:3000) dashboards
```
