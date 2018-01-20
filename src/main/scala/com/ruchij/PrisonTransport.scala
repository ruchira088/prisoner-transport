package com.ruchij

import com.ruchij.utils.ScalaUtils.group

object PrisonTransport
{
  def calculateCost(count: Int, pairs: List[(Int, Int)]) =
  {
    val prisonGroups = group(pairs)
    val groupPrisonerCount = prisonGroups.flatten.size

    prisonGroups.map(groupCost).sum + (count - groupPrisonerCount)
  }

  private def groupCost[T](group: Set[T]): Int =
    Math.ceil(Math.sqrt(group.size)).toInt
}
