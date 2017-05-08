package com.vsf.dashboardImpl.ui

import com.vsf.dashboard.{BaseMain, LoginView}
import com.vaadin.annotations.{PreserveOnRefresh, Theme, Title}
import com.vaadin.server._
import com.vaadin.shared.ui.label.ContentMode
import com.vaadin.ui.{CssLayout, Label, UI, Window}
import org.joda.time.DateTime
import org.slf4j.{Logger, LoggerFactory}

@Title("Proyecto")
@PreserveOnRefresh
@Theme("dashboard")
class MainUI extends UI with BaseMain {

  val app = DashboardApplication
  val logger : Logger = LoggerFactory.getLogger(this.getClass)

  setContent(rootLayout)
  setStyleName("login-bg")

  app.initializeIfNot


  override def init(request: VaadinRequest) : Unit = {
    super.init(request)
    setErrorHandler()
  }

  protected def createMainView() = new MainLayout(this)

  protected def createLoginView() : LoginView = {
    new LoginView(app.nameAndVersion, login) {
      if (app.isDeveloperMode) {
        setUsername(DashboardApplication.developerUsername)
        setPassword(DashboardApplication.developerPassword)
      }
    }
  }

  protected def closeCurrentSession() : Unit = {
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

  override def close() : Unit = {
    val sesion = Session.getCurrent
    if (sesion != null) {
      sesion.to = DateTime.now()
      Session.setCurrent(null)
    }
  }
}
