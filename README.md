# Quarkus primitive-array qualifier reproducer
 
This repository contains a minimal Quarkus application that reliably reproduces a `ClassCastException` in Quarkus Arc when comparing CDI qualifiers whose members are primitive arrays (e.g., `int[]`, `double[]`).

## Context
- **Quarkus**: 3.25.0  
- **Java**: 21  
- **Build tool**: Maven  
- **Extensions used**: quarkus-arc, quarkus-resteasy-reactive, quarkus-rest-client-reactive, quarkus-micrometer  

## Bug summary
When a custom CDI qualifier annotation declares a primitive array member, Arc’s `Qualifiers.hasQualifier(..)` attempts to cast the array to `Object[]`, which fails for primitive arrays. Same problem occurs with annotations like Micrometer’s `@Timed(percentiles = {0.5, 0.95})`.

### Observed exception
```

java.lang.ClassCastException: class \[D cannot be cast to class \[Ljava.lang.Object;
(\[D and \[Ljava.lang.Object; are in module java.base of loader 'bootstrap')
at io.quarkus.arc.impl.Qualifiers.hasQualifier(Qualifiers.java:...)

````

## What this project does
- Declares a CDI qualifier `@Flavor` with a primitive `double[]` member.
- Qualifies a `@RestClient` injection point with `@Flavor(pcts = {0.1, 0.2})`.
- Accesses the corresponding REST endpoint, which triggers the lazy initialization of the client proxy.

## Expected behavior
- The application should start, and the REST endpoint should return a successful response without errors.

## Actual behavior
- The application starts correctly, but throws a `ClassCastException` at runtime when the endpoint is first accessed. This prevents the REST client from being resolved and injected correctly.

## How to reproduce
1. Use Java 21.
2. Start the application in development mode:
   ```bash
   mvn quarkus:dev
   ```
3. Access the endpoint using a tool like `curl`:
   ```bash
   curl http://localhost:8080/greet
   ```
4. Observe the `ClassCastException` in the console where Quarkus is running.

## Project structure

* `src/main/java/org/acme/qualifiers/Flavor.java` – The CDI qualifier with a primitive array member.
* `src/main/java/org/acme/rest/GreetingResource.java` – The injection point for the REST Client and the REST endpoint.
* `src/main/java/org/acme/client/TimeClient.java` – The REST client interface.


## License

MIT
