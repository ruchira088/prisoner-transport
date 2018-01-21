package com.ruchij.exceptions

case class MismatchInputsException(expected: Int, actual: Int) extends Exception
{
  exception =>

  override def getMessage: String = exception.toString
}
