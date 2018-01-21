package com.ruchij.utils

import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

object ScalaUtils
{
  private implicit class SetUtils[T](set: Set[T])
  {
    def hasAtLeastOneValue(values: T*): Boolean = values.toList.exists(set.contains)
  }

  def group[T](pairsList: List[(T, T)]): Set[Set[T]] =
    pairsList.foldLeft(Set.empty[Set[T]]) {
      case (total, (a, b)) =>
        total.find(_.hasAtLeastOneValue(a, b))
          .fold(total + Set(a, b)) {
            set => total - set + (set ++ Set(a, b))
          }
    }

  def predicate(condition: Boolean, exception: => Exception): Try[Unit] =
    if (condition) Success((): Unit) else Failure(exception)

  def isInRange[T](min: T, max: T, value: T)(implicit ordering: Ordering[T]): Boolean =
    ordering.gteq(value, min) && ordering.lteq(value, max)

  def convert[T, R](converter: T => R, value: T): Try[R] =
    try {
      Success(converter(value))
    }
    catch {
      case NonFatal(throwable) => Failure(throwable)
    }

  def parseInt(intString: String) =
    convert[String, Int](_.toInt, intString)
}
