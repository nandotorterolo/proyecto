package com.vsf.dashboardImpl.ui.tabs

import com.vaadin.ui.Button.{ClickEvent, ClickListener}
import com.vaadin.ui.Notification

/**
  * Created by user on 5/6/17.
  */
class EmpleadoEditarTabImpl extends EmpleadoEditarTab {

  btGrabar.addClickListener(new ClickListener {
    override def buttonClick(event: ClickEvent) = Notification.show("hola mundo")
  })

}
