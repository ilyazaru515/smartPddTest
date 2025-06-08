До первого запуска обязательно в БД postgres надо руками содать схему и назвать ее 'pdd'

![1.png](1.png)

Чтобы в БД не затрались данные при каждом запуске spring boot приложения, в application.yml 
у конфига spring.jpa.hibernate.ddl-auto надо проставить update, что бы вернуть зачистку при каждом запуске, проставить create
```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: update
```