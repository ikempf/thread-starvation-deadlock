package com.ikempf

import java.net.URI
import java.net.http.HttpResponse.BodyHandlers
import java.net.http.{HttpClient, HttpRequest}

import cats.Parallel
import cats.effect.implicits._
import cats.effect.{Blocker, ContextShift, Effect, ExitCode, IO, IOApp, Sync}
import cats.implicits._

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    Blocker(Sync[IO])
      .use(blockingPool => new Service[IO](blockingPool).starvation)
      .flatMap(res => IO.delay(println(res)))
      .as(ExitCode.Success)

}

class Service[F[_]: Parallel: Effect: Sync: ContextShift](blockingPool: Blocker) {

  private val client: HttpClient = HttpClient.newHttpClient
  private val request            = HttpRequest.newBuilder.uri(URI.create("http://reddit.com/")).build

  def starvation: F[List[Int]] =
    Parallel.parSequence(List.fill(20)(blockingCallingBlocking))

  def blockingCallingBlocking: F[Int] =
//    ContextShift[F].blockOn(blockingPool)(
      Sync[F].delay(blockingPing)
//    )

  def blockingPing: Int =
    ping.toIO.unsafeRunSync()

  def ping: F[Int] =
    ContextShift[F].blockOn(blockingPool)(
      Sync[F].delay {
        client.send(request, BodyHandlers.ofString).statusCode()
      }
    )

}
