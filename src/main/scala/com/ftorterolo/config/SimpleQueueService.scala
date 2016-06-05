package com.ftorterolo.config

import java.util

import com.amazonaws.AmazonClientException
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.regions.{Region, Regions}
import com.amazonaws.services.sqs.AmazonSQSClient
import com.amazonaws.services.sqs.model.{MessageAttributeValue, SendMessageRequest, DeleteMessageRequest, ReceiveMessageRequest}
import com.ftorterolo.util.JsonUtil
import scala.collection.JavaConversions._
import scala.collection.mutable

/**
  * This sample demonstrates how to make basic requests to Amazon SQS using the
  * AWS SDK for Java.
  * <p>
  * <b>Prerequisites:</b> You must have a valid Amazon Web
  * Services developer account, and be signed up to use Amazon SQS. For more
  * information on Amazon SQS, see http://aws.amazon.com/sqs.
  * <p>
  * Fill in your AWS access credentials in the provided credentials file
  * template, and be sure to move the file to the default location
  * (~/.aws/credentials) where the sample code will load the credentials from.
  * <p>
  * <b>WARNING:</b> To avoid accidental leakage of your credentials, DO NOT keep
  * the credentials file in your source directory.
  *
  * https://github.com/aws/aws-sdk-java/blob/master/src/samples/AmazonSimpleQueueService/SimpleQueueServiceSample.java
  * https://coderwall.com/p/o--apg/easy-json-un-marshalling-in-scala-with-jackson
  */

// https://aws.amazon.com/es/sqs/pricing/
object SimpleQueueService extends App{

  try {
    val credentials: AWSCredentials = new ProfileCredentialsProvider().getCredentials

    val sqs = new AmazonSQSClient(credentials)
    val saEast1 = Region.getRegion(Regions.SA_EAST_1)
    sqs.setRegion(saEast1)

//    sqs.listQueues().getQueueUrls.foreach(println)

    val queueUrl = "https://sqs.sa-east-1.amazonaws.com/424370919947/SisDis"

    val res = sqs.getQueueAttributes(queueUrl, List("ApproximateNumberOfMessages"))
    res.getAttributes.foreach{case (u,v) => println(s"$u : $v")}

    val m1 = Mensaje(emisor=Detectores.D001.id, receptor=Detectores.D002.id, Map((Transportes.Auto.nombre,5), (Transportes.Moto.nombre,3)))
    val json = JsonUtil.toJson(m1)
//    println(json)

    val lamport:Array[Int] = Array(0,0,0,0)
    val jsonLamport = JsonUtil.toJson(lamport) // [0,0,0,0]

    val m2 = JsonUtil.fromJson[Mensaje](json)
//    println(m2)  // Mensaje(1,2,Map(5 -> 1, 2 -> 2))
//    println(MensajeExpandido(m2))  // MensajeExpandido(1D001,2D002,Map(5 -> Auto, 2 -> Moto))

    val messageAttributeValue = new MessageAttributeValue().withDataType("String").withStringValue(jsonLamport)
    val destinoMessageAttributeValue = new MessageAttributeValue().withDataType("String").withStringValue("Destino1")
    val messageAttributes : Map[String, MessageAttributeValue]= Map(("destino",destinoMessageAttributeValue),("lamport", messageAttributeValue))
//    val messageAttributes : Map[String, MessageAttributeValue]= Map(("lamport", messageAttributeValue))

    val request = new SendMessageRequest()
      .withMessageBody(json)
      .withQueueUrl(queueUrl)
      .withMessageAttributes(messageAttributes)
    sqs.sendMessage(request)


    val receiveMessageRequest = new ReceiveMessageRequest(queueUrl)
    // que cosas que queres que te traiga.
    val messages =  sqs.receiveMessage(receiveMessageRequest.withMessageAttributeNames(List("lamport", "destino"))).getMessages
//    val messages =  sqs.receiveMessage(receiveMessageRequest).getMessages

    println(s"messages received: ${messages.size()}")

    //  |${m.getMessageId}  |${m.getReceiptHandle}  |${m.getMD5OfBody}
    messages.foreach{ m => println(
      s"""
         |${m.getBody}
       """.stripMargin)
    }

    messages.foreach{ m=> m.getMessageAttributes.entrySet().foreach{ e =>  println(s"${e.getKey} : ${e.getValue.getStringValue}")} }

//    println(s"Size ${messages.size()}")

    // // Delete a message
    if (messages.size()>0) {
      val messageReceiptHandle = messages.get(0).getReceiptHandle
      sqs.deleteMessage(new DeleteMessageRequest(queueUrl, messageReceiptHandle))
      println(s"Deleted")
    }

  } catch {
    case e:Exception => {
      throw new AmazonClientException( "Cannot load the credentials from the credential profiles file. in location (~/.aws/credentials), and is in valid format.", e);
    }

  }

}
