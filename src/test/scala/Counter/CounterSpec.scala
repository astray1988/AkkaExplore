package Counter

import akka.actor.{Props, ActorSystem}
import akka.testkit.{ImplicitSender, TestProbe, TestKit}
import counter.Counter
import org.scalatest.{MustMatchers, BeforeAndAfterAll, FlatSpecLike}
import concurrent.duration._

/**
  * Created by dylan on 2/29/16.
  */
class CounterSpec extends TestKit(ActorSystem("test-system"))
  with FlatSpecLike
  with BeforeAndAfterAll
  with ImplicitSender
  with MustMatchers{

  override def afterAll = {
    TestKit.shutdownActorSystem(system)
  }

  "Counter Actor" should "handle GetCount message with using TestProbe" in {
    val sender = TestProbe()

    val counter = system.actorOf(Props[Counter])

    sender.send(counter, Counter.Increment)

    sender.send(counter, Counter.GetCount)

    val state = sender.expectMsgType[Int]

    state must equal(1)
  }

  it should "handle Increment message" in {
    val counter = system.actorOf(Props[Counter])

    counter ! Counter.Increment

    expectNoMsg(1.second)
  }

  it should "handle Decrement message" in {
    val counter = system.actorOf(Props[Counter])

    counter ! Counter.Decrement

    expectNoMsg(1.second)
  }

  it should "handle GetCount message" in {
    val counter = system.actorOf(Props[Counter])

    counter ! Counter.GetCount

    expectMsg(0)
  }

  it should "handle sequence of messages" in {
    val counter = system.actorOf(Props[Counter])

    counter ! Counter.Increment
    counter ! Counter.Increment
    counter ! Counter.Increment
    counter ! Counter.Decrement
    counter ! Counter.GetCount

    expectMsg(2)
  }


}
