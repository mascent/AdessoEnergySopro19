# Information for using the spring back-end
You'll need a postgres server running to use this spring boot application. The easiest way to get you running is using [Docker](https://www.docker.com/) following command for the default config (can be changed in `src/main/java/resources/application.properties`):

```
docker run --name sopro19-postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=sopro19 -p 5432:5432 -d postgres
```

After making sure you have a sufficent database installed (change the port if 5432 is already used) you can build the project by `mvn build`