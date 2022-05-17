## Для запуска автотестов предварительно должно быть установлено:
- IntelliJ IDEA
- IntelliJ Lombok plugin
- Docker Desktop
- Google Chrome

## Шаги запуска автотестов и просмотр отчета
### 1. Запустить контейнер командой
docker-compose up

### 2. Запустить jar файл командой
java -jar artifacts/aqa-shop.jar

### 3. Запустить тесты с отчетом Allure командой
./gradlew clean test allureReport

### 4. Запустить просмотр отчета Allure командой
./gradlew allureServe

## Для подключение к базе вручную использовать команду
docker-compose exec mysql mysql -u sergei -p travel  
password: password
