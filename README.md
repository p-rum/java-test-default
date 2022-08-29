# Java default test application

(_etnetera HR task_)

### Run instructions:

* using IntelliJ IDEA: simply run configuration [.run/Application.run.xml](.run/Application.run.xml)
* using another IDE:

    * run docker-compose up to start postgresql database
    * append following attributes to VM options:
      ```
      -Dspring.config.additional-location=file:.run/application-local.yaml
      ```

### REST API:

#### / frameworks:

* GET: returns list of all frameworks
* POST: create new JavaScript framework

___

#### / frameworks/{id}:

* GET: returns JS framework with given id
* PUT: update JS framework (only hypeLevel can be changed now)
* DELETE: delete JS framework

___

#### / frameworks/{id}/versions:

* GET: returns list of JS framework versions for JS framework with given id
* POST: create new JS framework version

___

#### / frameworks/{id}/versions/{versionId}:

* GET: returns JS framework version with given id
* PUT: update JS framework version (only deprecationDate can be changed now)
* DELETE: delete JS framework version
    
