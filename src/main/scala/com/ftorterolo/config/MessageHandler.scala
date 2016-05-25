package com.ftorterolo.config

import com.amazonaws.AmazonClientException
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.regions.{Regions, Region}
import com.amazonaws.services.sqs.AmazonSQSClient
import com.amazonaws.services.sqs.model._
import com.ftorterolo.util.JsonUtil
import org.slf4j.LoggerFactory
import scala.collection.JavaConversions._

object MessageHandler {

  val logger = LoggerFactory.getLogger("SendMessage")
  val queueUrl = "https://sqs.sa-east-1.amazonaws.com/424370919947/SisDis"
  val lamport = "lamport"

  val credentials: AWSCredentials = new ProfileCredentialsProvider().getCredentials
  val sqs = new AmazonSQSClient(credentials)
  val saEast1 = Region.getRegion(Regions.SA_EAST_1)
  sqs.setRegion(saEast1)

  def sendMessage(message:String, lamport:String) = {
    val messageAttributeValue = new MessageAttributeValue().withDataType("String").withStringValue(lamport)
    val messageAttributes : Map[String, MessageAttributeValue]= Map((MessageHandler.lamport,messageAttributeValue))

    val request = new SendMessageRequest()
      .withMessageBody(message)
      .withQueueUrl(queueUrl)
      .withMessageAttributes(messageAttributes)

    try {
      println(s"send: $message $lamport")
      sqs.sendMessage(request)
    } catch {
      case  imce: InvalidMessageContentsException => logger.error(s"No se pudo enviar el mensaje $message $lamport", imce)
      case  uoe: UnsupportedOperationException => logger.error(s"No se pudo enviar el mensaje $message $lamport", uoe)
    }
  }

  def receiveMessage(): Seq[Message] = {
    val receiveMessageRequest = new ReceiveMessageRequest(queueUrl)
    val messages =  sqs.receiveMessage(receiveMessageRequest.withMessageAttributeNames(MessageHandler.lamport)).getMessages
    messages
  }

  def deleteMessage(receiptHandle:String) = {
    try {
      sqs.deleteMessage(queueUrl, receiptHandle)
    } catch {
      case ife:InvalidIdFormatException => logger.error(s"Error at delete messege", ife)
      case rfe:ReceiptHandleIsInvalidException => logger.error(s"Error at delete message", rfe)
    }
  }


  /**
    * Indicates that the specified queue previously received a PurgeQueue request within the last 60 seconds,
    * the time it can take to delete the messages in the queue.
    */
  def purge() = {
    try {
      sqs.purgeQueue(new PurgeQueueRequest(queueUrl))
    } catch {
      case qdnee: QueueDoesNotExistException => logger.error(s"Error at purgeQueue", qdnee)
      case pqipe: PurgeQueueInProgressException => logger.error(s"Error at purgeQueue", pqipe)
    }
  }
}
