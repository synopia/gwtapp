# gwtapp - project configuration for a mvp gwt application

* uses http://pwt.putnami.org/
* multi module gradle configuration
* Guice/GIN

## Develop

    Terminal 1:
    $ ./gradlew jettyDraftWar

    Terminal 2:
    $ ./gradlew main:gwtSuperDev

Then go to http://localhost:9876/ and do the bookmark thing.

Finally, visit http://localhost:8080/ for your app.

