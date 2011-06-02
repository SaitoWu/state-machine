STATEMACHINE
============

an useless library for refactor ur messy if/else business logic.

Requirements
------------

 * Java 6

Mini DSL
--------

```java
State start = new State("start");
start.enter = new Signal(String.class).add(callee, true, "beep", "<============= start enter helloWorld ===============>");
start.exec = new Signal(String.class).add(callee, true, "beep", "<============= i am in start state ===============>");
start.to("service").when("a > 5");
start.to("task").when("a <= 5");
// new service state
State service = new State("service");
service.to("end");
// add enter and exit signal
service.enter = new Signal(String.class).add(callee, true, "beep", "<============= service enter helloWorld ===============>");
service.exec = new Signal(String.class).add(callee, true, "beep", "<============= i am in service state ===============>");
service.exit = new Signal(String.class).add(callee, true, "beep", "<============= service exit byeWorld ===============>");
// new task state
State task = new State("task");
task.enter = new Signal(String.class).add(callee, false, "beep", "<============= task enter helloWorld ===============>");
task.to("end");
// new end state
State end = new State("end");
end.exec = new Signal(String.class).add(callee, true, "beep", "<============= i am in end state ===============>");
```

Dot Support
-----------

Graphviz is open source graph visualization software.

#### install grapviz

on ubuntu or debian: 
    apt-get install graphviz 
others:
    http://www.graphviz.org/Download..php

#### gen dot file

    javac im.saito.Helper.java
    java im.saito.Helper

#### gen dot image

    dot -Tpng your/path/to/im/saito/Helper/fsm.dot > /your/path/to/fsm.png

wow:

<img src="http://dl.iteye.com/upload/picture/pic/91595/bbb57a3f-6bb7-3a81-9586-539821d046cf.png" width="400" alt="dot" />

Javascript Interpreter
----------------------

 * use it in Transition class / when method,true or false?

Running Statemachine
--------------------

 exec the src/example Main to run a statemachine.

Architecture Overview
---------------------

Statemachine is organized into two parts: a state machine and a java signals which is inspired by as3signals.each state has three inject point.(enter exec exit)u can use it do anything u want with signals.

Tips
----

 * Statemachine is not a "java like" java library. if u dont like this style. u can change it by urself.

Author
------------

 * Saito Wu <saitowu@gmail.com>

License
-------

Statemachine is licensed under the MIT License. (See LICENSE)
