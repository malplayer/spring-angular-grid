# Eligibility

This project is based on the VHA reference architecture. Currently only domain objects are implemented. The definitions for domain entities can be found in **domain/docs**.

The project represents a layered architecture, listed here from bottom to top.  Each subproject is contained in a subdirectory of the root directory (which contains this readme file).

* **parent-pom**:  Top-level pom that contains information for all subprojects; it is a peer to the subprojects.

* **support**:  Contains "core", "basic" or "commons" classes used by other subprojects.

* **test-support**:  Contains classes and configuration that are used during integration testing in subprojects.

* **domain**:  Contains persistent JPA entities and [Spring Data JPA](http://www.springsource.org/spring-data/jpa)-based repositories (sometimes called "data access objects" or "DAOs").  Entities typically
	* are stateful,
	* are lightweight, plain Java objects (POJOs),
	* contain
		* all domain logic when designing [behaviorally rich domain models](http://martinfowler.com/eaaCatalog/domainModel.html), OR
		* contain nothing but data when designing [anemic domain models](http://martinfowler.com/bliki/AnemicDomainModel.html) manipulated by [transaction scripts](http://martinfowler.com/eaaCatalog/transactionScript.html),
	* have identities composed of data that have no business value, which typically are surrogate keys of string or long integer types,
	* implement `equals` and `hashCode` using only identity fields,
	* don't use inheritance much, if at all,
	* have a design that is driven by the domain itself (and that is only *validated* by use cases).

* **dto**:  Contains [data transfer objects](http://en.wikipedia.org/wiki/Data_transfer_object) ("DTOs", sometimes also incorrectly called ["value objects" or "VOs"](http://c2.com/cgi/wiki?ValueObject)) used by the service layer.  DTOs typically
	* are serializable,
	* contain simple data types,
	* are completely devoid of behavior,
	* have a design that is driven by use case,
	* do not use inheritance,
	* represent the vocabulary spoken by the service layer.

* **service**:  Contains service interfaces and primary implementations.  [Services](http://martinfowler.com/eaaCatalog/serviceLayer.html) typically
	* are transactional if transactions are being used (that is, the service represents a transactional boundary),
	* are coarse-grained,
	* are request-response in nature, throwing no exceptions from methods,
	* maintain no client-specific state,
	* have a design that is driven by use case (where the user is either a human or machine client),
	* are defined as plain, old Java interfaces (POJIs) and are implemented by plain, old Java objects (POJOs),
	* take and return DTOs or simple types, trying to be designed with heterogeneous clients in mind,
	* are
		* thin & delegate to entities if employing a [behaviorally rich domain model](http://martinfowler.com/eaaCatalog/domainModel.html), OR
		* thick if employing an [anemic domain model](http://martinfowler.com/bliki/AnemicDomainModel.html) and a [transaction script pattern](http://martinfowler.com/eaaCatalog/transactionScript.html),
	* receive dependency injections from a container (like Spring).

* **rest**:  Contains rest controllers that receive incoming http requests.  Rest controllers typically
	* exist to adapt the rest protocol to the plain Java service layer,
	* are POJOs annotated with Spring MVC annotations,
	* are thin, delegating to underlying services,
	* use JSON or XML as the data interchange format.

* **web**:  Contains the web-based human- and machine-facing user interface.  The web layer typically
	* serves primarily dynamic content, but can also serve static content,
	* employs an MVC architecture based on Spring MVC and JSP or various other view technologies (JSF, Tiles, Freemarker, Velocity, etc),
	* enforces URL-based security via Spring Security,
	* renders views on the server-side for conventional, Model 2-based websites,
	* houses rest endpoints for rich internet applications (RIAs) or other, machine-based clients.

## Notes on Building
This is a pretty standard multimodule Maven project that uses Maven profiles to control its output and Spring bean profiles to configure the integration test environment.  The project uses a straightforward practice of aligning the names of the Maven profiles with the corresponding Spring bean profiles.

The profiles supported are:

* **derby**:  Used to activate the use of a local, embedded Apache Derby database.
* **sqlserver**:  since traditional SQL Server (as opposed to SQL Server Compact Edition or SQL Server LocalDB) requires a dedicated server process that only runs on Windows, this requires separate, dedicated infrastructure, preferably on localhost if the localhost is running Windows.  Otherwise, the `sqlserver` profile cannot be activated.  If using VirtualBox or VMWare to run a Windows guest with SQL Server Express (or any other edition), make sure the guest machine settings use bridged networking (as opposed to NAT or any other).  This gives the guest OS its own IP address on the same network that the host OS is on.  You would expect, then, that Microsoft's SQL Server JDBC Driver 4.0 would work using the guest's IPv4 address, but it doesn't.  Instead, the open source jTDS SQL Server driver must be used (and note that the latest version of the driver is for JDK7 only -- if you use JDK6, make sure to get the latest version of the driver for that).
* **oracle**: Oracle requires a dedicated server process. Oracle Express Edition is recommended for development and testing purposes but it only runs on Windows and Linux. If developing on a Mac, Oracle provides VMs that can be executed with VirtualBox. Running Oracle through a VM might be preferable to setting up an instance even if you are developing on Windows or Linux. Make sure you are using bridged networking in Virtual Box. 
* **datanucleus**:  Used to activate DataNucleus as the JPA implementation.
* **hibernate**:  Use to activate Hibernate as the JPA implementation.

Common build commands are:

`mvn clean install -P derby,datanucleus` (DataNucleus over embedded Derby)

`mvn clean install -P derby,hibernate` (Hibernate over embedded Derby)

`mvn clean install -P sqlserver,jtds,datanucleus` (DataNucleus over SQL Server via jTDS)

`mvn clean install -P sqlserver,msjdbc,datanucleus` (DataNucleus over SQL Server via MS JDBC)

`mvn clean install -P sqlserver,jtds,hibernate` (Hibernate over SQL Server via jTDS)

`mvn clean install -P sqlserver,msjdbc,hibernate` (Hibernate over SQL Server via MS JDBC)

`mvn clean install -P oracle,datanucleus` (DataNucleus over Oracle)

`mvn clean install -P oracle,hibernate` (Hibernate over Oracle)

Note that datanucleus requires that your persistent classes be enhanced and, as of this writing, that running using datanucleus with unenhanced classes will not work, and that running using hibernate with enhanced classes will not work, either.  If this becomes a problem, then we can look into either Maven build classifiers to distinguish enhanced from unenhanced artifacts or using datanucleus runtime enhancement.  It is worth noting that runtime enhancement significantly complicates app server configuration, especially in OSGi environments, and build-time enhancement is recommended.

### Build Infrastructure
It is traditionally bad practice to put `<repositories>` in your maven POMs; instead, you should configure a corporate Artifactory, Archiva, or Nexus repository proxy that your developers can use.  That way, any artifacts that aren't available in the standard or popular Maven places can be manually added to the corporate repository.  You also cut down on network bandwith usage when a new build or developer machine comes on line for the first time.

At the time of this writing, there is a `<repositories>` element in the POM in order to pick up the SciSpike Foundation repo.  Since that repo is password protected, you need to make sure to include your credentials in your local Maven settings file (~/.m2/settings.xml by default).

Further, the build requires the presence of the Microsoft JDBC jar.  For your convenience, that has been added to this project and can be found in ./3rd-party/, along with Unix & Windows scripts that install it into your local Maven repo (~/.m2/repository by default).  Installing this repo is a one-time process.

## Notes on Running
This build allows you to run the webapp *in situ*.  After successfully building from the root directory (the one containing this file), `cd web` and run `./go` or `go.bat` for Windows.  Make sure that you set or check the environment variable `SPRING_PROFILES_ACTIVE` -- it needs to be the same as the Maven profiles you used to build from the root directory.  It currently echoes its value to stdout when running the script, so you can see which profiles are being used.

Once you see a message like

	…
	[INFO] Started Jetty Server
	[INFO] Starting scanner at interval of 5 seconds.

then the webapp is up & running.  You can view it by going to <http://localhost:8080>.

### Server Certificates
If you get tired of the security warnings in the browser about self-signed certificates, you can always use your operating system and/or browser settings to trust the self-signed localhost certificate that the test site uses.  The certificate can be found at `./web/src/test/keystore/localhost.cer`.
