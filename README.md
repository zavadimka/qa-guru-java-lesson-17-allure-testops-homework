# Проект по тестированию русской версии [домашней страницы SoftGamings](https://www.softgamings.com/ru/)
В рамках набора простых автотестов проверяется фундаментальное поведение [домашней страницы SoftGamings](https://www.softgamings.com/ru/):
- [x] Корректная загрузка и проверка заголовка домашней страницы
- [x] Наличие важных разделов домашней страницы с правильными заголовками
- [x] Функциональность меню навигации по страницам, включая правильность заголовков, валидность и корректность открытия ссылок  

![This is an image](/media/images/Softgamings_home_page_screenshot.JPG)

____
## :pushpin: Содержание

- [Стек использованных технологий и инструментов](#computer-стек-использованных-технологий-и-инструментов)
- [Запуск тестов из командной строки](#arrow_forward-запуск-тестов-из-командной-строки)
- [Jenkins](#jenkins)
- [Allure Report](#allure-report)
- [Telegram-бот](#telegram-бот)
- [Allure TestOps](#allure-testops)
- [Jira](#jira)
- [Видео примера запуска тестов в Selenoid](#-видео-примера-запуска-теста-в-selenoid)

____
## :computer: Стек использованных технологий и инструментов

<p align="left">
    <a href="https://www.java.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/zavadimka/zavadimka/d7752c5e453307d9604abf6a8e76155f9338a14d/icons/Java.svg" alt="java" height="30" width="30"/> </a> 
    <a href="https://git-scm.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/zavadimka/zavadimka/d7752c5e453307d9604abf6a8e76155f9338a14d/icons/Git.svg" alt="git" width="30" height="30"/> </a> 
    <a href="https://gradle.org/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/zavadimka/zavadimka/d7752c5e453307d9604abf6a8e76155f9338a14d/icons/Gradle.svg" alt="gradle" height="30" width="30"/> </a> 
    <a href="https://junit.org/junit5/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/zavadimka/zavadimka/d7752c5e453307d9604abf6a8e76155f9338a14d/icons/Junit.svg" alt="junit" height="30" width="30"/> </a> 
    <a href="https://selenide.org/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/zavadimka/zavadimka/90aba27358b0550523685f7084b7cb2264586632/icons/Selenide.svg" alt="selenide" height="30" width="30"/> </a> 
    <a href="https://aerokube.com/selenoid/" target="_blank" rel="noreferrer"> <img src="" alt="selenoid" height="30" width="30"/> </a> 
    <a href="https://www.jenkins.io/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/zavadimka/zavadimka/3e5ee569abff7768f0da64b0e5195e1888c2602a/icons/Jenkins.svg" alt="jenkins" height="30" width="30"/> </a> 
    <a href="https://allurereport.org/" target="_blank" rel="noreferrer"> <img src="" alt="allurereport" height="30" width="30"/> </a> 
    <a href="https://qameta.io/" target="_blank" rel="noreferrer"> <img src="" alt="alluretestops" height="30" width="30"/> </a> 
    <a href="https://www.atlassian.com/software/jira" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/zavadimka/zavadimka/d7752c5e453307d9604abf6a8e76155f9338a14d/icons/Jira.svg" alt="jira" height="30" width="30"/> </a> 
    <a href="https://telegram.org/" target="_blank" rel="noreferrer"> <img src="" alt="telegram" height="30" width="30"/> </a> 
    <a href="https://www.jetbrains.com/idea/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/zavadimka/zavadimka/d7752c5e453307d9604abf6a8e76155f9338a14d/icons/IDEA.svg" alt="idea" width="30" height="30"/> </a> 
</p>

- [x] Автотесты написаны на языке `Java`.  
- [x] В качестве системы контроля версий используется `Git`.  
- [x] Для тестирования использованы фреймворки `JUnit 5` и `Selenide`.  
- [x] Инструментом для сборки Java-проекта является `Gradle`.  
- [x] Управление удаленным браузером осуществляется с помощью `Selenoid`.  
- [x] Для непрерывного выполнения тестовых сценариев используется `Jenkins`:  
    - с помощью `cron-расписания` каждые 5 минут инициируется удаленное выполнение тестовых сценариев,  
    - по результатам автоматизированных тестов с помощью `Allure Report` генерируется отчет, оповещение с результатами тестов и ссылкой на отчет направляется в `Telegram-бот`.  
- [x] Для управления тестированием, отслеживания результатов и анализа данных используется `Allure TestOps`.  
- [x] Для управления проектом выполнена интеграция с `Jira`.  

____
## :arrow_forward: Запуск тестов из командной строки
В проекте предусмотрено выполнение тестов локально и в удаленном браузере с помощью `Selenoid`, параметры драйвера передаются из соответствующего файла `properties`.

При выполнении команды тесты запустятся `удаленно` с помощью `Selenoid`:
```
gradle clean test
```

При выполнении команды тесты запустятся `локально`:
```
gradle clean test -Ddriver=local
```

После запуска теста на печать выводится информация о текущей конфигурации web-драйвера 

![This is an image](/media/images/WebDriver_parameters.JPG)

Параметры web-драйвера могут переопределены с помощью properties и параметров в `Jenkins`:
```
clean test
-Ddriver=${DRIVER}
-Dbrowser=${BROWSER}
-Dbrowser=${BROWSER_VERSION}
-Dbrowser=${BROWSER_SIZE}
```
Параметры сборки:

    DRIVER - локальный (local) или удалённый (remote) драйвер. По умолчанию - remote.
    BROWSER – браузер, в котором будут выполняться тесты. По умолчанию - chrome.
    BROWSER_VERSION – версия браузера, в котором будут выполняться тесты. По умолчанию - 122.0.
    BROWSER_SIZE – размер окна браузера, в котором будут выполняться тесты. По умолчанию - 1920x1080.

____
## Jenkins

На сервере `Jenkins` создан **[job](https://jenkins.autotests.cloud/job/%D0%A124-dmzjb-l17-allure-testops-homework/)** для непрерывного запуска параметризованных автотестов.  
<p align="center">
<img title="Jenkins job" src="media/images/Jenkins_job.JPG">
<img title="Jenkins parameters" src="media/images/Jenkins_job_parameters.JPG">
</p>

____
## Allure Report
По результатам каждого запуска автотестов генереруется аналогичный **[отчёт Allure Report](https://jenkins.autotests.cloud/job/%D0%A124-dmzjb-l17-allure-testops-homework/allure/#suites/8b77906daa6d50740b3ff0fd1183c0a9/5f4b72f0a117a9f/)**.  
<p align="center">
<img title="Allure report overview" src="media/images/Allure_report_overview.JPG">
</p>
В отчёте подробно описаны шаги автотестов:  
<p align="center">
<img title="Allure report steps" src="media/images/Allure_report_steps.JPG">
</p>
К отчёту также прикреплены вложения, в т.ч. видеозаписи автотестов:  
<p align="center">
<img title="Allure report attachments" src="media/images/Allure_report_attachments.JPG">
</p>

____
## Telegram-бот
По результатам выполнения автотестов отправляется уведомление с помощью `Telegram-бота` с основной информация и ссылкой на сгенерированный отчёт:  
<p align="center">
<img title="Telegram notification" src="media/images/Telegram_notification.JPG">
</p>

____
## Allure TestOps
Для управления тестированием, отслеживания результатов и анализа данных выполнена интеграция сборки `Jenkins` в `Allure TestOps`:
<p align="center">
<img title="Allure TestOps" src="media/images/Allure_TestOps.JPG">
</p>

____
## Jira
Выполнена интеграция `Jira`. В задаче отражается информация  из Allure TestOps о результатах выполнения тестов и выявленных дефектах:
<p align="center">
<img title="Jira task" src="media/images/Jira_task.JPG">
</p>