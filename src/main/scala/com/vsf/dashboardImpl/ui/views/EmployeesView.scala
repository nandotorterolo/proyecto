package com.vsf.dashboardImpl.ui.views

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent
import com.vaadin.ui.{VerticalLayout => VVerticalLayout}
import com.vsf.dashboardImpl.dao
import vaadin.scala._
import scala.concurrent.ExecutionContext.Implicits.global

class EmployeesView extends VVerticalLayout with View
{

  private var table: Table = null

  init(searchButtonEvent, clearButtonEvent)

  def init(searchButtonEvent:Button.ClickEvent => Unit, clearButtonEvent:Button.ClickEvent => Unit) {
    setSizeFull()
    addStyleName("dashboard-view")

    val tabSheet = new TabSheet()
    tabSheet.caption = "Empleados"
    tabSheet.addComponent(new VerticalLayout { vl =>
      spacing = true
      addStyleName("toolbar")

      addComponent(new HorizontalLayout { hl =>
        setSizeFull()
        spacing = true
        margin = true
        addComponent(new TextField {
          caption = Some("Nombre")
        })
        addComponent(new TextField {
          caption = Some("Apellido")
        })
        addComponent(new TextField {
          caption = Some("CI")
        })
        private val searchButton:Button= addComponent(new Button{
          caption = "Buscar"
          addStyleName("default")
          clickListeners += searchButtonEvent
        })

        private val clearButton:Button= addComponent(new Button{
          caption = "Limpiar"
          addStyleName("default")
          clickListeners += clearButtonEvent
        })

        hl.setAlignment(searchButton, Alignment.BottomCenter)
        hl.setAlignment(clearButton, Alignment.BottomCenter)
      })

      table = addComponent(new Table{ t=>
        setSizeFull()
        selectionMode = SelectionMode.Single

        addContainerProperty(TablePropertyId.name, classOf[String])
        addContainerProperty(TablePropertyId.surname, classOf[String])
        addContainerProperty(TablePropertyId.socialId, classOf[String])

        setColumnHeader(TablePropertyId.name, "Nombre")
        setColumnHeader(TablePropertyId.surname, "Apellido")
        setColumnHeader(TablePropertyId.socialId, "CI")
      })
    })

    addComponent(tabSheet.p)
  }

  def searchButtonEvent(e: Button.ClickEvent): Unit = {
    table.removeAllItems()
    table.selectionMode = SelectionMode.None
    dao.Employees.getEmployees.map(_.zipWithIndex.map {case (employ,i) =>
      (i, List(TablePropertyId.name -> employ.name, TablePropertyId.surname -> employ.surname, TablePropertyId.socialId -> employ.socialId))
    }).map(res => table.container = Container.apply(res:_*))
  }


  def clearButtonEvent(e: Button.ClickEvent): Unit = {
    table.removeAllItems()
   }

  def enter(event: ViewChangeEvent) : Unit = {

  }

}