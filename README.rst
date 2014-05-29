Widget Time
===========

An example Dropwizard + angular.js web app.

Quickstart
----------

This assumes that you have a recent version of Maven and some flavor of the
Java 7 JDK installed.

.. code-block:: shell-session

    user@host:~$ git clone https://github.com/malept/widget-time.git
    [...]
    user@host:~$ cd widget-time
    user@host:widget-time$ mvn package
    [...]
    user@host:widget-time$ java -jar target/widget-time-0.0.1-SNAPSHOT.jar server

Access http://localhost:8080 to view the app in "action".

Vagrant
-------

For consistency, a Vagrant environment has also been set up. It differs from a
"normal" development environment in that it automatically installs the JDK &
Maven, and uses nginx to serve up the static assets from the resources
directory, instead of from the JAR.

To use this method, install Vagrant ≥ 1.5.0 and run the following:

.. code-block:: shell-session

    user@host:widget-time$ vagrant up
    [...]
    user@host:widget-time$ vagrant ssh
    [...]
    vagrant@vagrant:~$ cd /vagrant
    vagrant@vagrant:/vagrant$ # same steps as above, starting with `mvn package`

You will be able to access the web app from http://localhost:9080 (note the
port number changed).
