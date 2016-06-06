package com.ftorterolo.SemDashboard.ui

import java.util.concurrent.TimeUnit

import com.ftorterolo.config.Detector_001
import com.ftorterolo.dashboard.{LoginView, BaseMain}
import com.google.gwt.thirdparty.guava.common.util.concurrent.AbstractScheduledService.Scheduler
import com.vaadin.annotations.{PreserveOnRefresh, Theme, Title}
import com.vaadin.server._
import com.vaadin.shared.ui.label.ContentMode
import com.vaadin.ui.{CssLayout, Label, UI, Window}
import org.joda.time.DateTime
import org.slf4j.LoggerFactory

@Title("Obligatorio - SisDis")
@PreserveOnRefresh
@Theme("dashboard")
class Main extends UI with BaseMain {

  val app = DashboardApplication
  val logger = LoggerFactory.getLogger(this.getClass)

  logger.info("Main extends UI with BaseMain => " + this)
  setContent(rootLayout)
  setStyleName("login-bg")

  app.initializeIfNot


  override def init(request: VaadinRequest) = {
    super.init(request)
    setErrorHandler()
  }

  protected def createMainView() = new MainView(this)

  protected def createLoginView() = {
    val view = new LoginView(app.nameAndVersion, login)
    if (app.isDeveloperMode) {
      view.setUsername(DashboardApplication.developerUsername)
      view.setPassword(DashboardApplication.developerPassword)
    }
    view
  }

  protected def closeCurrentSession() = {
    close()
  }

  protected def isLoggedIn = false

  private def setErrorHandler() {
    UI.getCurrent.setErrorHandler(new DefaultErrorHandler() {
      override def error(event: com.vaadin.server.ErrorEvent ) {
        // Find the final cause
        var cause = "Error:"
        var t = event.getThrowable
        logger.error(cause,t)
        while (t != null) {
          if (t.getCause == null) // We're at final cause
            cause += t.getClass.getName
          t = t.getCause
        }

        val errorW = new Window("Error", new CssLayout(new Label(cause, ContentMode.HTML)))
        errorW.setResizable(true)
        errorW.center()
        errorW.addStyleName("error-window")
        UI.getCurrent.addWindow(errorW)
      }
    })
  }

  override def close() = {
    val sesion = Session.getCurrent
    if (sesion != null) {
      sesion.hasta = DateTime.now()
      Session.setCurrent(null)
    }
  }
}
