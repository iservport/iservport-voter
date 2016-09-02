organization in ThisBuild := "com.iservport"

scalaVersion in ThisBuild := "2.11.8"



lazy val utils = project("utils")
  .settings(
    version := "1.0-SNAPSHOT"
//    ,libraryDependencies += lagomJavadslApi
  )

def project(id: String) = Project(id, base = file(id))
  .settings(
    scalacOptions in Compile += "-Xexperimental" // this enables Scala lambdas to be passed as Java SAMs
  )
  .settings(
    libraryDependencies ++= Seq(
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.7.3" // actually, only api projects need this
    )
  )

