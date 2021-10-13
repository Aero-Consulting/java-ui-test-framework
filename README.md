### Проект тестов для UI

#### Скачивание docker контейнеров с браузерами chrome и firefox
Выполнить команды

`docker pull selenoid/vnc_chrome:104.0`

`docker pull selenoid/vnc_firefox:104.0`

#### Указание путей до конфигурационных файлов
Перейти в директорию проекта открыть файл docker-compose.ui-tests.yaml

Указать абсолютный путь до папки build\allure-results

Указать абсолютный путь до папки config

#### Запуск тестов
Перейти в директорию проекта и выполнить команду

`docker compose -f docker-compose.ui-tests.yaml up --exit-code-from gradle`

#### Генерация отчета
Перейти в директорию проекта и выполнить команду

Windows

`allure serve build\allure-results`

Unix

`allure serve build/allure-results`

#### Просмотр прохождения тестов
Перейти в браузере по ссылке `http://localhost:8080/`

Выбрать сессию из списка и открыть её





