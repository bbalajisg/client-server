import sbtassembly.Plugin.AssemblyKeys
import AssemblyKeys._
import sbtassembly.Plugin._

assemblySettings

organization := "com.balab"

name := "cs-client-app"

version := "1.0"

scalaVersion := "2.10.3"

jarName in assembly :=  name +".jar"

crossScalaVersions := Seq( "2.10.1", "2.9.3")

scalaVersion <<= crossScalaVersions { (vs: Seq[String]) => vs.head }

libraryDependencies ++= Dependencies.libraries

resolvers += Resolver.url("local-ivy", new URL("file://Users/dev-env/.ivy2/local/.ivy2/local"))
