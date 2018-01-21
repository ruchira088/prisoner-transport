package com.ruchij

import com.ruchij.utils.ScalaUtils

import scala.util.Try

case class PrisonTransport(count: Int, pairs: List[(Int, Int)])

object PrisonTransport
{
  import ScalaUtils._
  import com.ruchij.exceptions.InputOutOfRangeException._

  val MAX_PRISONERS = 100000

  def calculateCost(count: Int, pairs: List[(Int, Int)]) =
  {
    val prisonGroups = group(pairs)
    val groupPrisonerCount = prisonGroups.flatten.size

    prisonGroups.map(groupCost).sum + (count - groupPrisonerCount)
  }

  private def groupCost[T](group: Set[T]): Int =
    Math.ceil(Math.sqrt(group.size)).toInt

  def validateAndCreate(count: Int, pairs: List[(Int, Int)]): Try[PrisonTransport] =
    for {
      _ <- predicate(isInRange(2, MAX_PRISONERS, count), s"2 <= count <= $MAX_PRISONERS")

      _ <- predicate(
        isInRange(1, Math.min(count * (count.toDouble - 1)/2, MAX_PRISONERS), pairs.length),
        s"1 <= numberOfPairs <= min(count*(count - 1)/2, $MAX_PRISONERS)"
      )

      max = pairs.map { case (x, y) => Math.max(x, y) }.max
      min = pairs.map { case (x, y) => Math.min(x, y) }.min

      _ <- predicate(min >= 1, "min(x, y) >= 1")
      _ <- predicate(max <= count, s"max(x, y) <= $count")

      _ <- predicate(!pairs.exists { case (x, y) => x == y }, "(x -> y) x != y")
    }
    yield PrisonTransport(count, pairs)
}
