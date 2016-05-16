package com.ftorterolo.SemDashboard.ui

import com.ftorterolo.dashboard.BaseApplication
import com.vaadin.server.Page
import com.vaadin.ui.UI
import org.joda.time.DateTime
import org.slf4j.{Logger, LoggerFactory}

object DashboardApplication extends BaseApplication {

  val logger: Logger = LoggerFactory.getLogger(getClass.getName)
  val name: String = "Obligatorio"
  val developerUsername: String = "Fernando"
  val developerPassword: String = "a123456"
  val companyName: String = "UCU"
  val version: String = "16.05.1"
  def isDeveloperMode = true

  logger.info("Application => " + this)

  protected def init: Boolean = {
    true
  }

  def login(userName: String, password: String): Either[Throwable, AppSession] = {

      Session.setCurrent(new Session(new DateTime(), "DEVELOPER"))
      val appSession = new AppSession {
        def id = 0
        def userName = "DEVELOPER"
        def start = new DateTime
      }
        Right(appSession)

  }

}

object Session
{
  val SessionKey = "SessionKey"
  def getCurrent = UI.getCurrent.getSession.getAttribute(SessionKey).asInstanceOf[Session]
  def setCurrent(ssn: Session) = UI.getCurrent.getSession.setAttribute(SessionKey, ssn)

  def browserApplication =  Page.getCurrent.getWebBrowser.getBrowserApplication
  def address = Page.getCurrent.getWebBrowser.getAddress
  def sesionDescription= "From: " + address

  // TODO: Definir que se guarda en la descripcion de la sesion:
  // def sesionDescription= "From: " + address + " in " + browserApplication
  //'From: 127.0.0.1 in Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/39.0.2171.65 Chrome/39.0.2171.65 Safari/537.36')

}

class Session {
  var desde: DateTime = _
  var hasta: DateTime = _
  var description: String = _

  def this(desde:DateTime, description:String) = {
    this()
    this.desde=desde
    this.description=description
  }

  override def toString = desde + " - " + hasta

}