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

For more details see [here](http://veithen.github.io/2013/05/26/github-bintray-maven-release-plugin.html)


## How to run the application

* Download the jar from [Bintray](https://bintray.com/rufer7/maven/biz.dfch.activiti.wrapper/view)
* Run the application by executing the following command (Maybe you have to adjust the version in the command)

`java -jar activiti.wrapper-0.0.1.jar --activiti.uri="SERVER_BASE_URI" --activiti.user="ACTIVITI_USER" --activiti.password="ACTIVITI_PASSWORD"`


**HINT**
To test the application install and run the [eternnoir/activiti docker image](https://registry.hub.docker.com/u/eternnoir/activiti/)

