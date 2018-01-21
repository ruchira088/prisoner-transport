package com.ruchij.exceptions

case class ParseException(message: String) extends Exception
{
  override def getMessage: String = message
}
