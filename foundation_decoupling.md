# SciSpike Foundation Decoupling

The VHA Eligibility project was recently de-coupled from the SciSpike Foundation library and the use of traits via AspectJ was replaced with abstract base classes and delegation. The following list contains what was replaced and a brief explanation of where the functionality now resides.

One outstanding issue that VHA needs to address is getting a Maven repository set up. There is a licensing restriction that prevents the Oracle and Microsoft JDBC drivers from being distributed on public repos like Maven Central and Spring IO. Those jars are still located on the SciSpike repository along with the Foundation jars. That's why the SciSpike repository is still listed in the parent pom.xml -- without it, dependency errors will occur during build time. Once VHA sets up a Maven repository, those jars can be relocated and the SciSpike repository can be safely removed from the parent pom.xml eliminating all references to SciSpike Foundation.

* **@ExpressesPersistable**: Functionality now resides in `BaseEntity` located in the domain project. Id generation is handled via `String`. All Eligibility domain objects now extend `BaseEntity`.
* **@ExpressesStringifiable**: Functionality now resides in `BaseEntity` located in the domain project. The style of default output can be modified here if one wishes.
* **@ExpressesLoggable**: `AbstractLoggable` introduced in the support project replaces this functionality
* **@ExpressesNameable**: Traditional field with getters/setters introduced in applicable entity classes
* **@ExpressesDescribable**: Traditional field with getters/setters introduced in applicable entity classes
* **@ExpressesHasEmails**: Functionality added directly to `Party` in domain project
* **@ExpressesHasPhones**: Functionality added directly to `Party` in domain project
* **@DataTransferObject**: Replaced by abstract class `DataTransferObject` in dto project. All DTOs extend this class now.
* **@Service**: SciSpike annotation extended the Spring `@Service` and offered some additional Javadocs. Now Eligibility uses the Spring `@Service` directly.
* **@ExpressesFlushable**: Functionality now resides in `AbstractService` located in the service project
* **@AutoRollback**: Functionality now resides in `AbstractService` located in the service project
* **@AutoHandleExceptions**: Functionality now resides in `AbstractService` and `ServiceExecution` located in the service project. The service methods that used to incorporate `@AutoHandleExceptions` now delegate to the `executeSafely` method. This method will return an appropriate `Response` following the same general principle that service methods should never throw exceptions but instead return an appropriate success or failure state.
* **Response**: Copied from Foundation to Eligibility located in service project
* **AbstractVersion**: Copied from Foundation to Eligibility located in support project
* **Repository**: Copied from Foundation to Eligibility located in domain project
* **StringUtils**: Copied from Foundation to Eligibility located in support project
* **Hasher**: Copied from Foundation to Eligibility located in support project
* **Hibernate Distribution**: hibernate-distro project copied from Foundation
* **Hibernate Schema Generator**: hibernate-tools project copied from Foundation
* **DataNucleus Distribution**: datanucleus-rdbms-distro copied from Foundation
* **General POM Cleanup**: Of course, all references to Foundation artifacts were either removed or replaced with Eligibility. The use of aspectj was completely removed and all compilation relies on javac now.

Also during this refactor, the existing sample domain objects `User` and `Person` were removed and the example login/registration page was instead wired up to use the `Individual` entity from the Eligibility domain.
