package com.ruchij

import com.ruchij.exceptions.{EmptyInputException, InsufficientInputsException, MismatchInputsException, ParseException}

import scala.util.matching.Regex
import scala.util.{Failure, Success, Try}

object InputParser
{
  import com.ruchij.utils.ScalaUtils._

  val PAIR_REGEX: Regex = "(\\d+) (\\d+)".r

  def parse(input: List[String]): Try[PrisonTransport] =
    input match {
      case Nil => Failure(EmptyInputException)
      case xs if xs.length < 3 => Failure(InsufficientInputsException(3, xs.length))
      case count :: pairCount :: pairings => transform(count, pairCount, pairings)
    }

  private def transform(countString: String, pairCountString: String, pairingsStringList: List[String]): Try[PrisonTransport] =
    for {
      count <- parseInt(countString)
      pairCount <- parseInt(pairCountString)
      pairs <- parsePairings(pairingsStringList)

      _ <- predicate(pairCount == pairs.length, MismatchInputsException(pairCount, pairs.length))

      prisonTransport <- PrisonTransport.validateAndCreate(count, pairs)
    }
    yield prisonTransport

  private def parsePairings(pairingsList: List[String], result: List[(Int, Int)] = List.empty): Try[List[(Int, Int)]] =
    pairingsList match {
      case pairString :: rest => parsePair(pairString).fold(Failure(_), pair => parsePairings(rest, result :+ pair))
      case Nil => Success(result)
    }

  private def parsePair(pairString: String): Try[(Int, Int)] =
    pairString.trim match {

      case PAIR_REGEX(xString, yString) =>
        for {
          x <- parseInt(xString)
          y <- parseInt(yString)
        }
        yield x -> y

      case _ => Failure(ParseException(s"$pairString !-> (x: Int, y: Int)"))
    }

}
