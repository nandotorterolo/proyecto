package com.ftorterolo.config

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.regions.{Region, Regions}
import com.amazonaws.services.sqs.AmazonSQSClient
import com.amazonaws.services.sqs.model._
import org.slf4j.LoggerFactory

import scala.collection.JavaConversions._

object MessageHandler {

  val logger = LoggerFactory.getLogger("MessageHandler")
//  val QueueUrl = "https://sqs.sa-east-1.amazonaws.com/424370919947/SisDis"
  val Lamport = "lamport"

  val credentials: AWSCredentials = new ProfileCredentialsProvider().getCredentials
  val sqs = new AmazonSQSClient(credentials)
  val saEast1 = Region.getRegion(Regions.SA_EAST_1)
  sqs.setRegion(saEast1)

  def sendMessage(queueUrl:String, message:String, lamport:String) = {
    val messageAttributeValue = new MessageAttributeValue().withDataType("String").withStringValue(lamport)
    val messageAttributes : Map[String, MessageAttributeValue]= Map((Lamport,messageAttributeValue))

    val request = new SendMessageRequest()
      .withMessageBody(message)
      .withQueueUrl(queueUrl)
      .withMessageAttributes(messageAttributes)

    try {
      val res = sqs.sendMessage(request)
      logger.debug(s"Send Message: $message $lamport $res")
    } catch {
      case  imce: InvalidMessageContentsException => logger.error(s"No se pudo enviar el mensaje $message $lamport", imce)
      case  uoe: UnsupportedOperationException => logger.error(s"No se pudo enviar el mensaje $message $lamport", uoe)
    }
  }

  def receiveMessage(queueUrl:String): Seq[Message] = {
//    println(s"send: receiveMessage
    val receiveMessageRequest = new ReceiveMessageRequest(queueUrl)
    val messages =  sqs.receiveMessage(receiveMessageRequest.withMessageAttributeNames(List(Lamport))).getMessages
//    println(s"size ${messages.size()}")
    messages
  }

  def deleteMessage(queueUrl:String, receiptHandle:String) = {
    try {
      val res = sqs.deleteMessage(queueUrl, receiptHandle)
      logger.debug(s"Delete message: $res")
    } catch {
      case ife:InvalidIdFormatException => logger.error(s"Error at delete messege", ife)
      case rfe:ReceiptHandleIsInvalidException => logger.error(s"Error at delete message", rfe)
    }
  }


  /**
    * Indicates that the specified queue previously received a PurgeQueue request within the last 60 seconds,
    * the time it can take to delete the messages in the queue.
    */
  def purge(queueUrl:String) = {
    try {
      val res = sqs.purgeQueue(new PurgeQueueRequest(queueUrl))
      logger.debug(s"Purge Queue message resuslt: $res")
    } catch {
      case qdnee: QueueDoesNotExistException => logger.error(s"Error at purgeQueue", qdnee)
      case pqipe: PurgeQueueInProgressException => logger.error(s"Error at purgeQueue", pqipe)
    }
  }
}
