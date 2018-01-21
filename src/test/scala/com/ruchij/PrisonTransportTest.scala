package com.ruchij

import com.ruchij.exceptions.InputOutOfRangeException
import org.scalatest.{FlatSpec, Matchers}

class PrisonTransportTest extends FlatSpec with Matchers
{
  import PrisonTransportTest._

  "PrisonTransport.calculateCost(Int, List[(Int, Int)])" should "return correct cost" in {

    val PrisonTransport(count, pairs) = 4 prisonersWithPairs (1 -> 2) and (1 -> 3)

    assertResult(3)(PrisonTransport.calculateCost(count, pairs))
  }

  "PrisonTransport.validateAndCreate(Int, List[(Int, Int)])" should "return Success PrisonTransport" in
  {
    PrisonTransport.validateAndCreate(4, List(1 -> 2, 1 -> 4)).get shouldEqual PrisonTransport(4, List(1 -> 2, 1 -> 4))
  }

  it should "return Failures" in
  {
    val scenarios: List[(Int, List[(Int, Int)])] =
      List(
        (PrisonTransport.MAX_PRISONERS + 1, List()),
        (2, List(1 -> 3)),
        (3, List(1 -> 1)),
        (2, List(-1 -> 2)),
        (2, List(1 -> 2, 2 -> 1))
      )

    scenarios.foreach {
      case (count, pairs) =>
        PrisonTransport.validateAndCreate(count, pairs).failed.get shouldBe a [InputOutOfRangeException]
    }
  }
}

object PrisonTransportTest
{
  implicit class PrisonTransportBuilder(prisonTransport: PrisonTransport)
  {
    def and(pair: (Int, Int)): PrisonTransport =
      PrisonTransport(prisonTransport.count, prisonTransport.pairs :+ pair)
  }

  implicit class ScenarioWrapper(prisonerCount: Int)
  {
    def prisonersWithPairs(pair: (Int, Int)): PrisonTransport =
      PrisonTransport(prisonerCount, List(pair))
  }
}
