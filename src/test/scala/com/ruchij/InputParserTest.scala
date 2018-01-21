package com.ruchij

import com.ruchij.exceptions.{EmptyInputException, InsufficientInputsException, MismatchInputsException, ParseException}
import org.scalatest.{FlatSpec, Matchers}

class InputParserTest extends FlatSpec with Matchers
{
  "InputParser.parse(List[String])" should "return Success PrisonTransport" in
  {
    InputParser.parse(List("4", "2", "1 2", "1 4")).get shouldEqual PrisonTransport(4, List(1 -> 2, 1 -> 4))
  }

  it should "return Failures" in
  {
    InputParser.parse(List.empty).failed.get shouldBe EmptyInputException

    InputParser.parse(List("1")).failed.get shouldBe a [InsufficientInputsException]

    InputParser.parse(List("1", "1", "1 2", "1 4")).failed.get shouldBe a [MismatchInputsException]

    InputParser.parse(List("1", "1", "1")).failed.get shouldBe a [ParseException]

    InputParser.parse(List("a", "2", "1 2")).failed.get shouldBe a [Exception]
  }
}
