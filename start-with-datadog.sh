#!/bin/bash

# ========================
# Configurações personalizáveis
# ========================
SERVICE_NAME="springboot-app"
ENVIRONMENT="dev"
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

echo "🔍 Lendo informações do projeto do pom.xml..."
# Extrai a versão e o nome do artefato dinamicamente do pom.xml
APP_VERSION=$(./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout)
ARTIFACT_ID=$(./mvnw help:evaluate -Dexpression=project.artifactId -q -DforceStdout)
JAR_NAME="${ARTIFACT_ID}-${APP_VERSION}.jar"
JAR_PATH="target/${JAR_NAME}"

echo "  - Versão da Aplicação: ${APP_VERSION}"
echo "  - Caminho do JAR: ${JAR_PATH}"

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