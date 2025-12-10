# Makefile para configurar .venv, instalar dependencias y correr tests

SHELL := /bin/zsh

# Load environment variables from .env file if it exists
# Variables can still be overridden: make VAR=value target
-include .env
export

PROJECT_DIR := $(PWD)
VENV := $(PROJECT_DIR)/.venv
PYTHON_BIN ?= python3
VENV_DIR := .venv
ACTIVATE := $(VENV)/bin/activate
PYTHON := $(VENV)/bin/python
PIP := $(VENV)/bin/pip
PYTEST := $(VENV)/bin/pytest
PY := $(VENV)/bin/python
REQ := requirements.txt

UNIT_TESTS := $(PROJECT_DIR)/app/tests/unit
FUNC_TESTS := $(PROJECT_DIR)/app/tests/functional
E2E_TESTS := $(PROJECT_DIR)/app/tests/e2e
INTEGRATION_TESTS := $(PROJECT_DIR)/app/tests/integration

# Variables para pruebas funcionales en modo live (puedes sobreescribir con make VAR=valor)
SAP_MIDDLEWARE_URL ?= http://localhost:9999
APIKEY_CGP_APIGEE ?= dummy
APIKEY_SAP_API_MANAGEMENT ?= dummy
SAP_API_MANAGEMENT_BASE_URL ?= http://sap.local
SAP_API_ENDPOINT ?= /odata
SAP_CLIENT ?= 100

.DEFAULT_GOAL := help

.PHONY: help venv upgrade-pip install dev-install activate shell run test test-unit test-functional test-functional-live clean \
	test-e2e test-e2e-docker test-integration test-integration-docker test-all show-config \
	docker-build docker-build-dev docker-build-test docker-test-image docker-run docker-run-dev docker-stop docker-logs \
	docker-shell docker-health docker-wait docker-smoke docker-size docker-clean docker-full-test docker-e2e-test docker-integration-test

help: ## Show this help
	@grep -E '^[a-zA-Z_-]+:.*?## ' $(firstword $(MAKEFILE_LIST)) | awk 'BEGIN {FS = ":.*?## "}; {printf "  \033[36m%-26s\033[0m %s\n", $$1, $$2}' || true
	@echo ""
	@echo "\033[1mTests Locales:\033[0m"
	@echo "  make test-unit            -> Tests unitarios (mocked)"
	@echo "  make test-functional      -> Tests funcionales (mocked)"
	@echo "  make test-e2e             -> Tests e2e (mocked)"
	@echo "  make test-integration     -> Tests integración (mocked)"
	@echo "  make test-all             -> Todos los tests"
	@echo ""
	@echo "\033[1mTests contra Docker:\033[0m"
	@echo "  make test-e2e-docker      -> E2E tests contra contenedor (requiere container corriendo)"
	@echo "  make docker-e2e-test      -> Pipeline: build → run → e2e → stop"
	@echo "  make docker-integration-test -> Pipeline: build → run → integration → stop"
	@echo "  make docker-all-tests     -> Pipeline: build → run → smoke + e2e → stop"
	@echo ""
	@echo "\033[1mDocker:\033[0m"
	@echo "  make docker-build         -> Imagen producción"
	@echo "  make docker-run           -> Levantar contenedor"
	@echo "  make docker-full-test     -> Pipeline: build → run → smoke → stop"

$(PYTHON):
	$(PYTHON_BIN) -m venv $(VENV)
	@$(VENV)/bin/pip install --upgrade pip setuptools wheel

venv: $(PYTHON) ## Create virtual environment
	@echo ".venv listo en $(VENV)"
	@echo "Run: source $(ACTIVATE)"

upgrade-pip: venv ## Upgrade pip inside venv
	@$(PY) -m pip install --upgrade pip

install: venv ## Install dependencies from requirements.txt
	$(PIP) install -r $(REQ)
	# Asegura dependencias de test comunes
	$(PIP) install pytest pytest-asyncio
	@echo "Instalación completa. Activa el entorno con: source $(VENV)/bin/activate"

dev-install: venv ## Install dev dependencies if requirements-dev.txt exists
	@if [ -f requirements-dev.txt ]; then $(PIP) install -r requirements-dev.txt; else echo "No requirements-dev.txt found"; fi

activate: ## Print activation command for current shell
	@echo "Use this to activate in your current shell:"
	@echo ""
	@echo "source $(ACTIVATE)"

shell: venv ## Start an interactive shell with venv activated
	@echo "Launching $(SHELL) with venv active..."
	@source $(ACTIVATE) && exec $(SHELL) -i

run: venv ## Run FastAPI locally with uvicorn (reload)
	@source $(ACTIVATE) && uvicorn app.main:app --host 0.0.0.0 --port 8000 --reload

test: venv ## Run tests with pytest
	@source $(ACTIVATE) && PYTHONPATH=$(PROJECT_DIR) $(PYTEST) -q $(PROJECT_DIR)/app/tests

test-unit: venv
	PYTHONPATH=$(PROJECT_DIR) $(PYTEST) -q $(UNIT_TESTS)

test-functional: venv
	PYTHONPATH=$(PROJECT_DIR) $(PYTEST) -q $(FUNC_TESTS)

test-functional-live: venv
	TEST_LIVE_HTTP=1 \
	SAP_MIDDLEWARE_URL=$(SAP_MIDDLEWARE_URL) \
	APIKEY_CGP_APIGEE=$(APIKEY_CGP_APIGEE) \
	APIKEY_SAP_API_MANAGEMENT=$(APIKEY_SAP_API_MANAGEMENT) \
	SAP_API_MANAGEMENT_BASE_URL=$(SAP_API_MANAGEMENT_BASE_URL) \
	SAP_API_ENDPOINT=$(SAP_API_ENDPOINT) \
	SAP_CLIENT=$(SAP_CLIENT) \
	PYTHONPATH=$(PROJECT_DIR) $(PYTEST) -q $(FUNC_TESTS)

test-e2e: venv ## Run e2e tests (mocked)
	PYTHONPATH=$(PROJECT_DIR) $(PYTEST) -v $(E2E_TESTS)

test-e2e-docker: venv ## Run e2e tests against Docker container (requires running container)
	TEST_LIVE_HTTP=1 \
	TEST_BASE_URL=http://localhost:$(HOST_PORT) \
	PYTHONPATH=$(PROJECT_DIR) $(PYTEST) -v $(E2E_TESTS)

test-integration: venv ## Run integration tests (mocked)
	PYTHONPATH=$(PROJECT_DIR) $(PYTEST) -v $(INTEGRATION_TESTS)

test-integration-docker: venv ## Run integration tests against Docker (requires running container)
	TEST_LIVE_HTTP=1 \
	TEST_BASE_URL=http://localhost:$(HOST_PORT) \
	SAP_MIDDLEWARE_URL=$(SAP_MIDDLEWARE_URL) \
	APIKEY_CGP_APIGEE=$(APIKEY_CGP_APIGEE) \
	APIKEY_SAP_API_MANAGEMENT=$(APIKEY_SAP_API_MANAGEMENT) \
	SAP_API_MANAGEMENT_BASE_URL=$(SAP_API_MANAGEMENT_BASE_URL) \
	SAP_API_ENDPOINT=$(SAP_API_ENDPOINT) \
	SAP_CLIENT=$(SAP_CLIENT) \
	PYTHONPATH=$(PROJECT_DIR) $(PYTEST) -v $(INTEGRATION_TESTS)

test-all: venv ## Run all tests (unit, functional, e2e, integration)
	PYTHONPATH=$(PROJECT_DIR) $(PYTEST) -v $(PROJECT_DIR)/app/tests

clean: ## Remove virtual environment and cache files
	@rm -rf $(VENV) $(VENV_DIR) .pytest_cache **/__pycache__ **/*.pyc

show-config: ## Show current configuration (from .env and defaults)
	@echo "\033[1m=== Docker Configuration ===\033[0m"
	@echo "  IMAGE_NAME:      $(IMAGE_NAME)"
	@echo "  IMAGE_TAG:       $(IMAGE_TAG)"
	@echo "  CONTAINER_NAME:  $(CONTAINER_NAME)"
	@echo "  HOST_PORT:       $(HOST_PORT)"
	@echo "  CONTAINER_PORT:  $(CONTAINER_PORT)"
	@echo ""
	@echo "\033[1m=== SAP Configuration ===\033[0m"
	@echo "  SAP_MIDDLEWARE_URL:         $(SAP_MIDDLEWARE_URL)"
	@echo "  SAP_API_MANAGEMENT_BASE_URL: $(SAP_API_MANAGEMENT_BASE_URL)"
	@echo "  SAP_API_ENDPOINT:           $(SAP_API_ENDPOINT)"
	@echo "  SAP_CLIENT:                 $(SAP_CLIENT)"
	@echo "  APIKEY_CGP_APIGEE:          $$(echo '$(APIKEY_CGP_APIGEE)' | head -c 8)..."
	@echo "  APIKEY_SAP_API_MANAGEMENT:  $$(echo '$(APIKEY_SAP_API_MANAGEMENT)' | head -c 8)..."
	@echo ""
	@if [ -f .env ]; then echo "\033[32m✓ .env file found\033[0m"; else echo "\033[33m⚠ No .env file (using defaults)\033[0m"; fi


# ==============================
# Docker targets
# ==============================
# These can be overridden via .env file or command line: make IMAGE_TAG=v1.0.0 docker-build
IMAGE_NAME ?= iamge
IMAGE_TAG ?= latest
CONTAINER_NAME ?= container
HOST_PORT ?= 8080
CONTAINER_PORT ?= 8080


# Build targets (single-stage Dockerfile)
docker-build: ## Build imagen de producción
	docker build -t $(IMAGE_NAME):$(IMAGE_TAG) -f app/Dockerfile .

docker-build-dev: ## Build imagen de desarrollo
	docker build -t $(IMAGE_NAME):dev -f app/Dockerfile .

docker-build-test: ## Build imagen de tests
	docker build -t $(IMAGE_NAME):test -f app/Dockerfile .

docker-test-image: docker-build-test ## Ejecutar tests dentro de Docker
	docker run --rm $(IMAGE_NAME):test

docker-run: docker-stop ## Levantar contenedor de producción
	@if [ -f .env ]; then \
	  docker run -d --rm --name $(CONTAINER_NAME) \
	    -p $(HOST_PORT):$(CONTAINER_PORT) \
	    --health-cmd="curl -f http://localhost:8080/health || exit 1" \
	    --health-interval=30s \
	    --env-file .env \
	    $(IMAGE_NAME):$(IMAGE_TAG); \
	else \
	  docker run -d --rm --name $(CONTAINER_NAME) \
	    -p $(HOST_PORT):$(CONTAINER_PORT) \
	    --health-cmd="curl -f http://localhost:8080/health || exit 1" \
	    --health-interval=30s \
	    $(IMAGE_NAME):$(IMAGE_TAG); \
	fi

docker-run-dev: docker-stop ## Levantar contenedor de desarrollo con volumen montado
	@if [ -f .env ]; then \
	  docker run -d --rm --name $(CONTAINER_NAME)-dev \
	    -p $(HOST_PORT):$(CONTAINER_PORT) \
	    -v $(PROJECT_DIR)/app:/code/app:ro \
	    --env-file .env \
	    $(IMAGE_NAME):dev; \
	else \
	  docker run -d --rm --name $(CONTAINER_NAME)-dev \
	    -p $(HOST_PORT):$(CONTAINER_PORT) \
	    -v $(PROJECT_DIR)/app:/code/app:ro \
	    $(IMAGE_NAME):dev; \
	fi

docker-stop: ## Detener contenedor
	-@docker stop $(CONTAINER_NAME) >/dev/null 2>&1 || true
	-@docker stop $(CONTAINER_NAME)-dev >/dev/null 2>&1 || true

docker-logs: ## Ver logs del contenedor
	docker logs -f $(CONTAINER_NAME)

docker-shell: ## Abrir shell en el contenedor (debug)
	docker exec -it $(CONTAINER_NAME) /bin/bash

docker-health: ## Verificar estado de salud del contenedor
	@docker inspect --format='{{.State.Health.Status}}' $(CONTAINER_NAME) 2>/dev/null || echo "Container not running"

docker-wait: ## Esperar a que el contenedor esté listo
	@echo "Esperando a que el contenedor esté listo en http://localhost:$(HOST_PORT)..."; \
	  for i in $$(seq 1 30); do \
	    if curl -fsS http://localhost:$(HOST_PORT)/health >/dev/null; then echo "OK - Healthy"; exit 0; fi; \
	    sleep 1; \
	  done; \
	  echo "Timeout esperando al contenedor"; exit 1

docker-smoke: ## Smoke tests básicos contra el contenedor
	@echo "=== Health Check ==="; \
	  curl -s http://localhost:$(HOST_PORT)/health | python3 -m json.tool || true; \
	  echo "\n=== Handshake ==="; \
	  curl -s http://localhost:$(HOST_PORT)/handshake | python3 -m json.tool || true

docker-size: ## Mostrar tamaño de las imágenes Docker
	@docker images $(IMAGE_NAME) --format "table {{.Repository}}\t{{.Tag}}\t{{.Size}}"

docker-clean: ## Limpiar imágenes Docker locales
	-docker rmi $(IMAGE_NAME):$(IMAGE_TAG) $(IMAGE_NAME):dev $(IMAGE_NAME):test 2>/dev/null || true
	docker image prune -f

docker-full-test: docker-build docker-run docker-wait docker-smoke docker-stop ## Test completo: build + run + smoke + stop
	@echo "✅ Pipeline de tests Docker completado"

docker-e2e-test: docker-build docker-run docker-wait test-e2e-docker docker-stop ## E2E tests against Docker: build → run → e2e → stop
	@echo "✅ E2E tests contra Docker completados"

docker-integration-test: docker-build docker-run docker-wait test-integration-docker docker-stop ## Integration tests against Docker
	@echo "✅ Integration tests contra Docker completados"

docker-all-tests: docker-build docker-run docker-wait docker-smoke test-e2e-docker docker-stop ## All tests against Docker
	@echo "✅ Todos los tests contra Docker completados"



