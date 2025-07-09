# ========================
# Etapa 1: Build
# ========================
FROM node:22.9.0-alpine3.20 AS builder

# Instala pnpm globalmente
RUN npm install -g pnpm

WORKDIR /app

# Copia los archivos necesarios para instalar dependencias
COPY package.json pnpm-lock.yaml ./

# Instala las dependencias
RUN pnpm install --frozen-lockfile

# Copia todo el proyecto
COPY . .

# Construye la app (Next.js con output: 'standalone')
RUN pnpm build

# Prepara carpeta con archivos opcionales (public, static)
RUN mkdir -p /app/standalone-extra/public /app/standalone-extra/static && \
    [ -d public ] && cp -r public/* /app/standalone-extra/public/ || true && \
    [ -d .next/static ] && cp -r .next/static/* /app/standalone-extra/static/ || true



# ========================
# Etapa 2: Runtime
# ========================
FROM node:22.9.0-alpine3.20 AS runner

WORKDIR /app

# Copia el output standalone
COPY --from=builder /app/.next/standalone ./

# Copia contenido opcional solo si existe
COPY --from=builder /app/standalone-extra/public ./public
COPY --from=builder /app/standalone-extra/static ./.next/static

# Variables de entorno
ENV NODE_ENV=production
ENV PORT=3000

# Exponer puerto
EXPOSE 3000

# Comando de inicio (generado por Next.js)
CMD ["node", "server.js"]
