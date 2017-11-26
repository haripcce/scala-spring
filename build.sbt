name := "spring-scala"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies += "org.springframework.boot" % "spring-boot-starter" % "1.4.1.RELEASE"
libraryDependencies += "org.springframework.boot" % "spring-boot-starter-test" % "1.4.1.RELEASE"
libraryDependencies += "org.springframework.boot" % "spring-boot-starter-web" % "1.4.1.RELEASE"
// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa
libraryDependencies += "org.springframework.boot" % "spring-boot-starter-data-jpa" % "1.4.1.RELEASE"
// https://mvnrepository.com/artifact/org.hsqldb/hsqldb
//libraryDependencies += "org.hsqldb" % "hsqldb" % "1.8.0.10" % Test
// https://mvnrepository.com/artifact/mysql/mysql-connector-java
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.9"
