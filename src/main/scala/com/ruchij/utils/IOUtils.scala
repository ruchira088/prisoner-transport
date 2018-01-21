package com.ruchij.utils

import java.nio.ByteBuffer
import java.nio.channels.{AsynchronousFileChannel, CompletionHandler}
import java.nio.file.{Path, StandardOpenOption}

import scala.concurrent.{ExecutionContext, Future, Promise}

object IOUtils
{
  def readFile(path: Path): Future[Array[Byte]] =
  {
    val promise = Promise[Array[Byte]]

    val channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ)
    val byteBuffer = ByteBuffer.allocate(channel.size().toInt)

    channel.read(byteBuffer, 0, byteBuffer, new CompletionHandler[Integer, ByteBuffer]
    {
      override def failed(throwable: Throwable, attachment: ByteBuffer): Unit =
        promise.failure(throwable)

      override def completed(result: Integer, attachment: ByteBuffer): Unit =
        promise.success(byteBuffer.array())
    })

    promise.future
  }

  def readTextFile(path: Path)(implicit executionContext: ExecutionContext): Future[List[String]] =
    readFile(path).map(new String(_).trim.split("\n").toList)
}
