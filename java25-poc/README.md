# Java 25 POC

Demonstrates features introduced or finalized in Java 25.

## How to Run

```bash
mvn clean compile

mvn exec:exec -Dexec.mainClass="org.karane.ModuleImportExample"
mvn exec:exec -Dexec.mainClass="org.karane.User"
mvn exec:exec -Dexec.mainClass="org.karane.PemExample"
java src/main/java/org/karane/Hello.java

# Scoped Values
mvn exec:exec -Dexec.mainClass="org.karane.ScopedValuesExample"
mvn exec:exec -Dexec.mainClass="org.karane.ScopedValuesNormalThreadsExample"
mvn exec:exec -Dexec.mainClass="org.karane.ScopedValuesVirtualThreadsExample"
mvn exec:exec -Dexec.mainClass="org.karane.ScopedValuesRebindingExample"
mvn exec:exec -Dexec.mainClass="org.karane.ScopedValuesCallWhereExample"
mvn exec:exec -Dexec.mainClass="org.karane.ScopedValuesIsolationExample"
```