#!/bin/bash

# ========================
# Configurações personalizáveis
# ========================
SERVICE_NAME="springboot-app"
ENVIRONMENT="dev"
APP_VERSION="1.0.0"
JAR_NAME="backing-0.0.1-SNAPSHOT.jar"
JAR_PATH="target/$JAR_NAME"
DD_AGENT_JAR="dd-java-agent.jar"

# Leia a chave da API do Datadog de uma variável de ambiente para segurança.
# Antes de executar, defina a variável: export DATADOG_API_KEY='sua_chave_aqui'
if [ -z "$DATADOG_API_KEY" ]; then
  echo "❌ Erro: A variável de ambiente DATADOG_API_KEY não está definida."
  exit 1
fi

# ========================
# 1. Limpa e instala o projeto
# ========================
echo "🔄 Limpando e instalando o projeto com o Maven Wrapper..."
./mvnw clean install -DskipTests

if [ $? -ne 0 ]; then
  echo "❌ Erro ao compilar o projeto."
  exit 1
fi

# ========================
# 2. Baixa o agente se necessário
# ========================
if [ ! -f "$DD_AGENT_JAR" ]; then
  echo "⬇️ Baixando o agente Datadog Java..."
  curl -L -o "$DD_AGENT_JAR" https://dtdg.co/latest-java-tracer
else
  echo "✅ Agente Datadog já está presente."
fi

# ========================
# 3. Inicia a aplicação com Datadog Agent
# ========================
echo "🚀 Iniciando aplicação com Datadog Agent..."
java \
  -javaagent:./$DD_AGENT_JAR \
  -Ddd.service="$SERVICE_NAME" \
  -Ddd.env="$ENVIRONMENT" \
  -Ddd.version="$APP_VERSION" \
  -Ddd.logs.injection=true \
  -Ddd.trace.enabled=true \
  -Ddd.api.key="$DATADOG_API_KEY" \
  -jar "$JAR_PATH"

# ========================
# 4. Espera ao final da execução
# ========================
read -p "⏸ Pressione [Enter] para encerrar o script..."
echo "🛑 Encerrando o script."