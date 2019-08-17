# Cubano Demo Project

This project contains examples of how to use the [Cubano Framework](https://github.com/concordion/cubano).  Cubano is part of the [Concordion Family](https://github.com/concordion).  

To get the demo project working, follow the Section below > [Set up and run this project](#set-up-and-run-this-project).

Complement the examples in this project, with a read of:
* an [introduction to Concordion](http://tinyurl.com/concordion2)
* the [Cubano documentation](https://concordion.org/cubano) and in particular the [Cubano Framework Guide](https://concordion.org/cubano/framework)
* the [Concordion documentation](https://concordion.org/) and especially the [Concordion Hints and Tips page](https://concordion.org/technique/java/markdown/)

Once you are ready to start writing your own tests, pull down the [Cubano Template](https://github.com/concordion/cubano-template) project and adapt this base to start automating your project.

## Features

A goal of Cubano is to generate living documentation that is applicable to a wide audience.

With the specifications written in plain language, and styled using Concordion, the generated [living documentation](https://concordion.github.io/cubano-demo/example/Example.html) is readable by anyone:

> <img src="https://github.com/concordion/cubano/raw/master/docs/img/spec.png" alt="image of documentation" width="40%">

The storyboard feature allows users to easily see the steps that are performed to implement the specification, and has proven useful for product owners, analysts and training staff:

> <img src="https://github.com/concordion/cubano/raw/master/docs/img/storyboard-link.png" alt="image of documentation" width="30%">

> <img src="https://github.com/concordion/cubano/raw/master/docs/img/storyboard-expanded.png" alt="image of documentation" width="60%">

It is also useful for showing the contents of API messages:

> <img src="https://github.com/concordion/cubano/raw/master/docs/img/api.png" alt="image of documentation" width="60%">

Finally, the living documentation includes log messages that are useful for developers and test developers:

> <img src="https://github.com/concordion/cubano/raw/master/docs/img/log-links.png" alt="image of documentation" width="20%">

> <img src="https://github.com/concordion/cubano/raw/master/docs/img/log.png" alt="image of documentation" width="70%">

> <img src="https://github.com/concordion/cubano/raw/master/docs/img/log-image.png" alt="image of documentation" width="70%">


## Set up and run this project  
* Ensure you have a [Git client](https://git-scm.com/downloads) and [Gradle](https://gradle.org/) installed (or use via your IDE or other tools)
* Install [Firefox](https://www.mozilla.org/en-US/firefox/new/).  The default browser used in this demo project is Firefox.
* Install [Chrome](https://www.google.com/chrome/).  Used for at least one test.
* `cd` to a relevant folder
* `git clone https://github.com/concordion/cubano-demo.git` or download and unzip this project
* Import as a Gradle or Maven project into your IDE (e.g. for Eclipse, ensure [Buildship](http://www.eclipse.org/buildship) is installed, then File > Import > Gradle > Existing Gradle Project > follow the wizard)

## A note on Configuration Files
The default configuration for this project is pulled from `config.properties`.  Default configuration can be overriden by introducing a `user.properties` file.  Simply override properties in `user.properties` as required (useful for multi developer teams).  The project supplies a `user.properties.template` which can be used for this purpose.

## A note on Browsers
By default this project is set up to use the Firefox browser (with the Switch Browser fixture additionally using Chrome).

The property `webdriver.browserprovider`, in config.properties controls the default browser (e.g. `webdriver.browserprovider = FirefoxBrowserProvider`).

To modify the default browser, update property `webdriver.browserprovider` in either:
* the config.properties file
* or the user.properties file

Additional `[BrowserName]BrowserProviders` can be found in package `org.concordion.cubano.driver.web.provider.*`. 

Two other important classes to review are:
* `org.concordion.cubano.driver.web.config.WebDriverConfig` > Reads and supplies properties from the config.properties file that is required by the framework
* `org.concordion.cubano.driver.web.provider.LocalBrowserProvider` > Base class for local browser providers.

Further information on browser support and configuration can be found in the [Cubano documentation on Browser Providers](https://concordion.org/cubano/browser/providers)

## To Execute Tests
Concordion fixtures use the JUnit library, with a specialised ConcordionRunner (`@RunWith(ConcordionRunner.class)`).  This annotation is part of the class hierarchy from `ConcordionIndex` or `ConcordionFixture`, which all Fixtures inherit from.

### From an IDE (e.g. Eclipse)
* Class `Example` will run the full suite.  Run as per any standard JUnit fixture.
* Classes that end with `*Fixture` can be executed in the same way

### Using Gradle 
1. Ensure you are in the `cubano-demo` root directory.
1. From the command line run either:
    * `gradlew clean test -Dtest.single=example/Example -Dlogback.configurationFile=logback-jenkins.xml` (runs a single test, which co-ordinates the test suite)
    * `gradlew clean test` (runs all tests in the suite individually)
1. View the Concordion output under the subfolder `build\reports\spec\example\Example.html`

### Using Maven
1. Ensure you are in the `cubano-demo` root directory and that you've download and installed maven (this has been tested with 3.*).
1. From cmd line run `mvn test`
1. View the Concordion output under the subfolder `target\concordion\example\Example.html`

## Dealing with Proxies
If working from behind a proxy, then you will need to manage some proxy configuration as specified below, for Gradle, Service and Browser Testing, and Eclipse.

### Gradle
For an initial example, see `gradle.properies`, in the root directory, and update the `systemProp.*` parameters as required. For additional configuration options see [accessing the web via a proxy](https://docs.gradle.org/current/userguide/build_environment.html#sec:accessing_the_web_via_a_proxy).

### Service and Browser Testing
For an initial example, see `config.properies`, in the root directory, and update the `proxy.*` parameters as required. You must set `proxy.required = true` to use any format of proxy configuration. 

The built in Cubano class `org.concordion.cubano.config.ProxyConfig`, provides three forms of Proxy Management (choose one):
* Setting the Proxy from a Config File (which is the mechanism in this project - see below)
* Setting the Proxy from System Properties
* Setting the Proxy from  Environment Variables


### With Eclipse
`Eclipse > Window > Preferences > General > Network Connections`

* Add Manual for Http and Https: 
  * host: [proxyHost]
  * port: [proxyPort]
  * authentication: required
  * proxy bypass: [hostsToBypass]

## Feedback
We love to receive feedback.

Please report any [issues](https://github.com/concordion/cubano-demo/issues) to this Github project.

Please post other feedback, questions and discussion to the [Concordion Google Group](https://groups.google.com/forum/#!search/concordion) with "[Cubano]" in the message header.
