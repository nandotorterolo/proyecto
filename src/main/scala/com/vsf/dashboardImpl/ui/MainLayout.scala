package com.vsf.dashboardImpl.ui

import com.vsf.dashboardImpl.ui.views.{DashboardView, EmployeesView}
import com.vsf.dashboard.BaseMainLayout
import com.vaadin.navigator.Navigator
import com.vaadin.shared.ui.label.ContentMode
import com.vaadin.ui.MenuBar.Command
import com.vaadin.ui._

class MainLayout(main: Main) extends BaseMainLayout {

  protected val logo: Component = new Label("<span>" + main.app.nameAndVersion + "</span>" + main.app.companyName, ContentMode.HTML)

  protected val navigator: Navigator = new Navigator(main, contentLayout)

  protected val mainMenu: MainMenu = MainMenu(
    "Dashboard" -> classOf[DashboardView],
    "Empleados" -> classOf[EmployeesView]
  )

  protected val usuarioCommand: Command = new Command {
    def menuSelected(selectedItem: MenuBar#MenuItem): Unit = {
      getUI.addWindow(new Window("test"))
    }
  }

  init()

  protected def nombreUsuario = "userName"

  protected def logout(): Unit = {
    main.logout()
  }

}
