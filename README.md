[![License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg)](https://github.com/dfch/biz.dfch.j.activiti.wrapper/blob/master/LICENSE)
# biz.dfch.j.activiti.wrapper

REST wrapper for Activiti workflow invocation

## Release manual

1. Add the following server to your maven `settings.xml`
```
<server>
  <id>bintray</id>
  <username>{bintray-user}</username>
  <password>{bintray-api-key}</password>
</server>
```
2. Build the project
* `mvn -Prelease clean install`

3. Add a new version to the Bintray package
4. Release the application by executing the following commands
* `mvn release:prepare`
* `mvn release:perform`


## How to run the application

* Build the jar by executing `mvn clean install` on the project root
* Run the application by executing the following command

`java -jar activiti.wrapper-0.0.1-SNAPSHOT.jar --activiti.uri="SERVER_BASE_URI" --activiti.user="ACTIVITI_USER" --activiti.password="ACTIVITI_PASSWORD"`

