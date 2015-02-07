package com.balab.client


import akka.actor.{ActorLogging, Actor }

import com.balab.common.message.{TaskAssigned, MessageSender, ReceiveHeartBeat, MessageReceiver}

import scala.concurrent.duration._


class ClientActor extends Actor with ActorLogging{

  val serverActor =  context.actorSelection("akka.tcp://ServerActorSystem@127.0.0.1:2552/user/serverActor")

  def receive = {


    case op: MessageSender =>


      println("Sending Heart Beat " + op.toString)
      serverActor ! op

    case result1: MessageReceiver => result1 match {

      case ReceiveHeartBeat(msg) =>
        println("Received Heart Beat " + msg)
        context.stop(sender())

      case TaskAssigned(msg) => {
        println("Processing the given task")

        import context.dispatcher
        context.system.scheduler.scheduleOnce(1.minute) {
          print("Task completed...")

        }
      }
    }
  }
}
