FROM ubuntu:latest
LABEL authors="aditya"

# Install dependencies
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven \
    nodejs \
    npm \
    curl \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy backend source
COPY pom.xml ./
COPY src ./src

# Copy frontend source
COPY angular-app ./angular-app

# Build Spring Boot backend
RUN mvn clean package -DskipTests

# Build Angular frontend
WORKDIR /app/angular-app
RUN npm install && npm run build

WORKDIR /app

# Expose ports for backend and frontend
EXPOSE 8080 4200

# Create entrypoint script to handle runtime secrets
RUN echo '#!/bin/bash\n\
java -Dllm.mistral.baseApiUrl=${LLM_MISTRAL_BASE_API_URL} \n\
     -Dllm.mistral.apiKey=${LLM_MISTRAL_API_KEY} \n\
     -jar target/backend-app.jar &\n\
npm serve --prefix angular-app' > /entrypoint.sh && chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]
