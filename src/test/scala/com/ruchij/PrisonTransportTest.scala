package com.ruchij

import org.scalatest.FlatSpec

class PrisonTransportTest extends FlatSpec
{
  import PrisonTransportTest._

  "PrisonTransport.calculateCost" should "return correct cost" in {
    val prisonerPairs = 4 prisoners (1 -> 2) and (1 -> 3)

    println(prisonerPairs)
  }
}

object PrisonTransportTest
{
  case class Scenario(prisonerCount: Int, pairs: List[(Int, Int)])
  {
    def and(pair: (Int, Int)) = Scenario(prisonerCount, pairs :+ pair)
  }

  implicit class ScenarioWrapper(prisonerCount: Int)
  {
    def prisoners(pair: (Int, Int)): Scenario = Scenario(prisonerCount, List(pair))
  }
}
