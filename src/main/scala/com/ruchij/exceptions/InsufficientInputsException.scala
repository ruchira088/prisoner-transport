package com.ruchij.exceptions

case class InsufficientInputsException(expected: Int, actual: Int) extends Exception
{
  override def getMessage: String = s"Expected least number of inputs = $expected, actual: $actual"
}
