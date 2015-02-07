package com.balab.client

import com.balab.common.message.SendHeartBeat

import scala.concurrent.duration._
import com.typesafe.config.ConfigFactory

import akka.actor.ActorSystem
import akka.actor.Props

object ClientApplication {

  def main(args: Array[String]): Unit = {

    //if (args.isEmpty || args.head == "Creation")
    startRemoteCreationSystem()
  }


  def startRemoteCreationSystem(): Unit = {

    val system =
      ActorSystem("ClientSystem", ConfigFactory.load("clientmachine"))

    val actor =
      system.actorOf(Props[ClientActor], name = "clientActor")

    var inc:Int = 0;
    println("Started ClientSystem...")


    import system.dispatcher
    system.scheduler.schedule(2.second, 1.second) {

      actor ! SendHeartBeat({inc += 1; inc})
    }

  }
}
