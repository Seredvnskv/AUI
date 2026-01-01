FROM nginx:latest

LABEL authors="SEREDVNSKV"

ENV API_URL=http://localhost:8080/api

ADD dist/frontend/browser /usr/share/nginx/html/
ADD nginx.conf /etc/nginx/templates/default.conf.template

EXPOSE 4200

CMD ["nginx", "-g", "daemon off;"]
