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

## Widgets, presenter and services

* `user`
    * `UserService` login and session handling
    * `LoginComposite` displays login/logout box
* `contacts`
    * `ContactService` crud service for contacts
    * `ContactComposite` displays contact information
    * `ContactPresenter` crud editor for contacts
    * `ContactSelector` smart selector for contacts
* `accounts`
    * `AccountService` crud service for accounts
    * `AccountComposite` displays account information
    * `AccountPresenter` crud editor for accounts
    * `AccountSelector` smart selector for accounts
* `transfer`
    * `TransferService` creates new account transfers
    * `TransferPresenter` wizard like dialog to create complex transfers
* `main`
    * shows main panel and binds all presenters

