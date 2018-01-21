//package com.ruchij.utils
//
//import java.nio.ByteBuffer
//import java.nio.channels.{AsynchronousFileChannel, CompletionHandler}
//import java.nio.file.{Files, Path, Paths, StandardOpenOption}
//import java.util.UUID
//
//import org.scalatest.{AsyncFlatSpec, Matchers}
//
//import scala.concurrent.{Future, Promise}
//import scala.reflect.io.File
//
//class IOUtilsTest extends AsyncFlatSpec with Matchers
//{
//  "IOUtils.readTextFile(Path)" should "return text file contents" in
//  {
//    val textContent = List("Hello", "World")
//    val tempFilePath = Files.createFile(Paths.get(s"input-${uuid()}.txt"))
//
//    for {
//      _ <- writeToFile(tempFilePath, textContent.mkString("\n"))
//
//      readText <- IOUtils.readTextFile(tempFilePath)
//
//      _ = new File(tempFilePath.toFile).delete()
//    }
//    yield assertResult(textContent)(readText)
//  }
//
//  def uuid(): String = UUID.randomUUID().toString
//
//  def writeToFile(path: Path, content: String): Future[Integer] =
//  {
//    val promise = Promise[Integer]
//
//    val channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE)
//    val byteBuffer = ByteBuffer.wrap(content.getBytes)
//
//    channel.write(byteBuffer, 0, (): Unit, new CompletionHandler[Integer, Unit] {
//
//      override def failed(throwable: Throwable, attachment: Unit): Unit =
//        promise.failure(throwable)
//
//      override def completed(result: Integer, attachment: Unit): Unit =
//        promise.success(result)
//    })
//
//    promise.future
//  }
//}
