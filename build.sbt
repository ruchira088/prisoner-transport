import Dependencies._

lazy val ItTest = config("it") extend Test

lazy val root = (project in file("."))
  .configs(ItTest)
  .settings(
    inThisBuild(List(
      organization := "com.ruchij",
      scalaVersion := "2.12.4"
    )),
    name := "prison-transport",
    inConfig(ItTest)(Defaults.testSettings),
    libraryDependencies ++= Seq(
      scalaTest % Test,
      pegdown % Test
    )
  )

coverageEnabled := true

testOptions in Test +=
  Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/test-results")

addCommandAlias("testWithCoverage", ";clean ;test ;it:test ;coverageReport")