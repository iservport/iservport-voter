# iservport-voter
API for vote simulation

This is a sample project to demonstrate CQRS/ES and Lagom Framework. ES stands for Event Sourcing and CQRS means 
Command Query Responsibility Segregation. You will find Lagom Framework distribution 
[here](http://www.lagomframework.com/). One excellent source of information to start with is this free 
[e-book](http://www.oreilly.com/programming/free/reactive-microservices-architecture.html) by Jonas Bonér.

In short, Lagom is a quick way to get up and runing with CQRS/ES. You get a Service and a Persistence API, and a 
Development/Production environment as well. You benefit from asynchronous IO/communication and distributed 
persistence just from day one.

The sample project was inspired by a Community Project in Brazil, named [Politikei](http://www.politikei.org/), and its 
goal is to collect votes prior to the actual ballot in legislative houses. Here we will only deal with documents and 
vote counts, leaving other use cases to the original application.

# Running the sample

1. install Lightbend [Activator](https://www.lightbend.com/activator/download) (or [SBT](http://www.scala-sbt.org/) )

1. clone the code to your machine

1. run `activator runAll` from the command line inside the project root directory


After a few minutes (1st run may take several) you will see something like this:

```bash
[info] Loading project definition from /Users/mauriciofernandesdecastro/workspace/iservport-voter/project
Missing bintray credentials /Users/ (omitted)
[info] Starting embedded Cassandra server
......
[info] Cassandra server running at 127.0.0.1:4000
[info] Service locator is running at http://localhost:8000
[info] Service gateway is running at http://localhost:9000
[warn] Credentials file /Users/ (omitted)
[info] Service doc-impl listening for HTTP on 0:0:0:0:0:0:0:0:26422
[info] (Service started, use Ctrl+D to stop and go back to the console...)

```

Please, pay attention to the port, you message may be slightly different. Now, open another terminal and run:

```curl
curl -H "Content-Type: application/json" -X POST -d '{"id":"1","entityId":"cmcwb","docCode":"07-287-1000","docName":"Document One","docAbstract":"Proposition Short Description","issueDate": 1455026400000, "authorId":"mauricio@iservport.com", "authorName":"Maurício Castro", "docType":"P", "docContent":"Actual content", "voters": [] }' http://localhost:26422/api/docs
```

And then, read the result:

```curl
curl http://localhost:26422/api/docs/1

{"id":"1","entityId":"cmcwb","docCode":"07-287-1000","docName":"Document One","docAbstract":"Proposition Short Description","issueDate":1455026400000,"authorId":"mauricio@iservport.com","authorName":"Maurício Castro","docType":"P","docContent":"Actual content","voters":[]}
``` 

# License

Copyright 2016 i-Serv Consultoria Empresarial Ltda.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

# Blog

If you'e interested in Scala, visit our [blog](http://scalacamp.com.br/) (Portuguese).
