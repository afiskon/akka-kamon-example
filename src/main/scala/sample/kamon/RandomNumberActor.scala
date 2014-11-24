package sample.kamon

import akka.actor._
import RandomNumberActor._
import java.security.SecureRandom

import kamon.Kamon
import kamon.metric.UserMetrics

/**
 * just doing some work
 */
class RandomNumberActor extends Actor {

  import context._

  var primes: ActorRef = _
  val genNumberCounter = Kamon(UserMetrics).registerCounter("generate-number")

  override def preStart() {
    primes = actorOf(Props[Primes], "primes")
  }

  def receive = {
    case GenerateNumber =>
      genNumberCounter.increment()
      val n = (scala.math.random * 10000000).toLong
      primes ! n
    case GenerateSecureNumber =>
      val secure = new SecureRandom(System.currentTimeMillis.toHexString.getBytes)
      val n = (secure.nextDouble * 10000000).toLong
      primes ! n
    case PrimeFactors(factors) => // ignore
  }
}

object RandomNumberActor {

  case object GenerateNumber
  case object GenerateSecureNumber
  case class PrimeFactors(factors: List[Long])
}