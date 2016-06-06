package com.ftorterolo.SemDashboard.ui

import com.ftorterolo.SemDashboard.ui.views.{CorredorView, DashboardView}
import com.ftorterolo.dashboard.BaseMainView
import com.vaadin.ui.MenuBar.Command
import com.vaadin.navigator.Navigator
import com.vaadin.ui._
import com.vaadin.shared.ui.label.ContentMode

class MainView (main: Main) extends BaseMainView {

  protected val logo: Component = new Label("<span>" + main.app.nameAndVersion + "</span>" + main.app.companyName, ContentMode.HTML)

  protected val navigator: Navigator = new Navigator(main, contentLayout)

  protected val mainMenu: MainMenu = MainMenu(
    "Dashboard" -> classOf[DashboardView],
    "Corredor"-> classOf[CorredorView]
  )

  protected val usuarioCommand: Command = new Command {
    def menuSelected(selectedItem: MenuBar#MenuItem) = {
      getUI.addWindow(new Window("test"))
    }
  }

  init()

  /**
    * Protect visual overflow
    */
  protected def nombreUsuario = "userName"

  protected def logout() = {
    main.logout()
  }

}
