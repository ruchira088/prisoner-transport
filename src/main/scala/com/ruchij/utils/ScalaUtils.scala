package com.ruchij.utils

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
}
