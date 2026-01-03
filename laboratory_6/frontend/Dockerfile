FROM node AS builder

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

FROM nginx:1.23.3

LABEL authors="SEREDVNSKV"

ENV API_URL=http://localhost:8080/api

COPY --from=builder /app/dist/frontend/browser /usr/share/nginx/html/
ADD nginx.conf /etc/nginx/templates/default.conf.template

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
