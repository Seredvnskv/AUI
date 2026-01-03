# Instrukcja uruchomienia aplikacji z Docker Compose

## Wymagania
- Docker
- Docker Compose

## Uruchomienie

### 1. Zbuduj i uruchom wszystkie kontenery:
```bash
docker-compose up --build
```

Lub w tle:
```bash
docker-compose up -d --build
```

### 2. Poczekaj aż wszystkie serwisy się uruchomią (około 1-2 minuty)

## Dostęp do API

Po uruchomieniu aplikacji możesz testować API w przeglądarce:

### Gateway (główny punkt dostępu):
- **URL**: http://localhost:8080
- **Swagger UI** (jeśli skonfigurowane): http://localhost:8080/swagger-ui.html

### Model Management Service:
- **URL**: http://localhost:8081
- **H2 Console**: http://localhost:8081/h2-console
- **Health Check**: http://localhost:8081/actuator/health

### Brand Management Service:
- **URL**: http://localhost:8082
- **H2 Console**: http://localhost:8082/h2-console
- **Health Check**: http://localhost:8082/actuator/health

### Frontend:
- **URL**: http://localhost

## Przykładowe endpointy API

### Brands (przez Gateway):
- `GET http://localhost:8080/api/brands` - lista wszystkich marek
- `GET http://localhost:8080/api/brands/{id}` - szczegóły marki
- `POST http://localhost:8080/api/brands` - utworzenie nowej marki
- `PUT http://localhost:8080/api/brands/{id}` - aktualizacja marki
- `DELETE http://localhost:8080/api/brands/{id}` - usunięcie marki

### Models/Cars (przez Gateway):
- `GET http://localhost:8080/api/cars` - lista wszystkich modeli
- `GET http://localhost:8080/api/cars/{id}` - szczegóły modelu
- `POST http://localhost:8080/api/cars` - utworzenie nowego modelu
- `PUT http://localhost:8080/api/cars/{id}` - aktualizacja modelu
- `DELETE http://localhost:8080/api/cars/{id}` - usunięcie modelu

## Zatrzymanie aplikacji

```bash
docker-compose down
```

Jeśli chcesz usunąć również wolumeny:
```bash
docker-compose down -v
```

## Sprawdzenie statusu kontenerów

```bash
docker-compose ps
```

## Logi

Wyświetl logi wszystkich serwisów:
```bash
docker-compose logs
```

Logi konkretnego serwisu:
```bash
docker-compose logs gateway
docker-compose logs brand-management
docker-compose logs model-management
docker-compose logs frontend
```

## Troubleshooting

### Jeśli kontenery nie startują:
1. Sprawdź logi: `docker-compose logs`
2. Upewnij się, że porty 80, 8080, 8081, 8082 nie są zajęte
3. Spróbuj restart: `docker-compose restart`

### Jeśli potrzebujesz przebudować obrazy:
```bash
docker-compose down
docker-compose build --no-cache
docker-compose up
```

