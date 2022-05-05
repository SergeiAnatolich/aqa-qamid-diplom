# запуск контейнера
docker-compose up

# запуск jar файла
java -jar artifacts/aqa-shop.jar

# подключение к базе
docker-compose exec mysql mysql -u sergei -p travel