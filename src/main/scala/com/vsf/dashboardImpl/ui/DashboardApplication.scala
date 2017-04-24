package com.vsf.dashboardImpl.ui

import com.vsf.dashboard.BaseApplication
import com.vaadin.server.Page
import com.vaadin.ui.UI
import org.joda.time.DateTime
import org.slf4j.{Logger, LoggerFactory}

object DashboardApplication extends BaseApplication {

  val logger: Logger = LoggerFactory.getLogger(getClass.getName)
  val name: String = "Area Segura"
  val developerUsername: String = "developer"
  val developerPassword: String = "a123456"
  val companyName: String = "VFS"
  val version: String = "17.04.1"
  def isDeveloperMode = true

  protected def init: Boolean = {
    true
  }

  def login(userName: String, password: String): Either[Throwable, AppSession] = {

    // TODO define if application should handle sessions
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

  // TODO: define which values should session contain
  // def sesionDescription= "From: " + address + " in " + browserApplication
  //'From: 127.0.0.1 in Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/39.0.2171.65 Chrome/39.0.2171.65 Safari/537.36')

}

class Session {
  var from: DateTime = _
  var to: DateTime = _
  var description: String = _

  def this(desde:DateTime, description:String) = {
    this()
    this.from=desde
    this.description=description
  }

  override def toString = from + " - " + to

}