package com.ruchij.exceptions

case class InputOutOfRangeException(message: String) extends Exception
{
  override def getMessage: String = message
}

object InputOutOfRangeException
{
  implicit def create(message: String) = InputOutOfRangeException(message)
}
