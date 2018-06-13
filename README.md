# cubano-demo

Contains examples for projects wishing to use the Cubano framework. This generates living documentation for basic web and API services.

## To Execute Tests
Concordion fixtures use the JUnit library, with a specialised ConcordionRunner (`@RunWith(ConcordionRunner.class)`).  This annotation is part of the class hierarchy from `ConcordionIndex` or `ConcordionFixture`, 
which all Fixtures inherit from:
* Class `Example` will run the full suite.  Run as per any standard JUnit fixture.
* Classes that end with `*Fixture` can be executed in the same way

## Features

A goal of Cubano is to generate living documentation that is applicable to a wide audience.

With the specifications written in plain language, and styled using Concordion, the generated [living documentation](https://concordion.github.io/cubano-template/example/Example.html) is readable by anyone:

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
* `cd` to a relevant folder
* `git clone https://github.com/concordion/cubano-demo.git` or download and unzip this project
* To execute tests, run `gradlew clean test`

## Dealing with Proxies
If working from behind a proxy, then you will need to manage some proxy configuration as specified below, for Gradle, Service and Browser Testing, and Eclipse.

### Gradle
For an initial example, see `gradle.properies`, in the root directory, and update the `systemProp.*` parameters as required. For additional configuration options see [accessing the web via a proxy](https://docs.gradle.org/current/userguide/build_environment.html#sec:accessing_the_web_via_a_proxy).

### Service and Browser Testing
For an initial example, see `config.properies`, in the root directory, and update the `proxy.*` parameters as required. You must set `proxy.required = true` to use any format of proxy configuration. For additional configuration options see `org.concordion.cubano.config.ProxyConfig`

### With Eclipse
`Eclipse > Window > Preferences > General > Network Connections`

* Add Manual for Http and Https: 
  * host: [proxyHost]
  * port: [proxyPort]
  * authentication: required
  * proxy bypass: [hostsToBypass]
