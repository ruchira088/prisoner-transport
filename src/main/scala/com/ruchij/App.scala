package com.ruchij

object App
{
  def main(args: Array[String]): Unit =
  {
    println(PrisonTransport.calculateCost(4, List(1 -> 2, 1 -> 4)))
  }
}
