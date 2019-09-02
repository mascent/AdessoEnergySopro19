Spring Boot + Thymeleaf project template by Alexander Krause (akr), Henning Schnoor (hs),
and Sandro Esquivel (sae) for CAU Kiel Software Project

# How to start
- Import to Eclipse with **File -> Import... -> Maven -> Existing Maven Projects**
- (Optional) If Eclipse shows errors upon importing, try **Right click project -> Maven -> Update Project...**
- Run with **Right click -> Run as** on target `Start-Application` in run-config directory 
- Run tests with **Right click -> Run as** on target `Test-Application` in run-config directory 
- (Optional) Run with **Right click project -> Run As -> Spring Boot App** (Alt+Shift+X, B)
- (Optional) Run tests with **Right click project -> Run As -> JUnit Test** (Alt+Shift+X, T)
- Access locally running application via web browser at `localhost:8080`

# Note on Spring Security
- Spring Security is disabled in this project by default, i.e., all pages can be accessed.
- To enable security aspects like user authentication, edit the project file **pom.xml** and
  uncomment the dependencies for Spring Security (line 58 ff.).
- Afterwards, all pages need user authentication by default. Without specifying a custom user
  details service, Spring Boot defines a default user with username "user" and a randomly
  generated password that is shown in the console when the application starts.
- See the demo project in GitLab for an example how to implement a custom user details service.

# Employed technologies
- [Spring Boot](https://spring.io/projects/spring-boot)
- [H2 Database Engine](http://www.h2database.com/html/main.html)
- [Thymeleaf](https://www.thymeleaf.org)
- [Bootstrap](https://getbootstrap.com)
- [JBehave](https://jbehave.org/)

# Further information
- [Beginner's guide to JPA](https://vladmihalcea.com/a-beginners-guide-to-jpa-and-hibernate-cascade-types/)
- [One2Many vs. Many2Many simple explanation](https://stackoverflow.com/questions/21407402/one-to-many-vs-many-to-many-relationship)
- [Why you should not use CascadeType.REMOVE in Many2Many](https://thoughts-on-java.org/avoid-cascadetype-delete-many-assocations/)

# Guides at spring.io
The following guides provide detailed information how to use certain features:

- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
- [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
- [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
- [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
- [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
- [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)