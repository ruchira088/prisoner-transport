package com.ruchij

import java.nio.file.{Path, Paths}

import com.ruchij.utils.{IOUtils, ScalaUtils}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object App
{
  val INPUT_FILE_PATH: Path = Paths.get("input.txt")

  def main(args: Array[String]): Unit =
  {
    val result = for {
      input <- IOUtils.readTextFile(INPUT_FILE_PATH)

      PrisonTransport(count, pairs) <- Future.fromTry(InputParser.parse(input))
    }
    yield PrisonTransport.calculateCost(count, pairs)

    println(Await.result(result, 30 seconds))
  }
}
