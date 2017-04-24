package com.vsf.dashboardImpl.ui.views

import com.vaadin.event.ItemClickEvent
import com.vaadin.event.ItemClickEvent.ItemClickListener
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent
import com.vaadin.ui.{VerticalLayout => VVerticalLayout}
import com.vsf.dashboardImpl.dao
import vaadin.scala._

import scala.concurrent.ExecutionContext.Implicits.global

class EmployeesView extends VVerticalLayout with View
{

  var itemTableId: Option[Int] = None

  val nameTextField = new TextField {
    caption = Some("Nombre")
  }
  val surnameTextField = new TextField {
    caption = Some("Apellido")
  }
  val socialIdTextField  = new TextField {
    caption = Some("CI")
  }

  private val tabSheet = new TabSheet() {
    caption = "Empleados"
  }

  private val table: Table = new Table{ t=>
    setSizeFull()
    selectionMode = SelectionMode.Single

    addContainerProperty(TablePropertyId.name, classOf[String])
    addContainerProperty(TablePropertyId.surname, classOf[String])
    addContainerProperty(TablePropertyId.socialId, classOf[String])

    setColumnHeader(TablePropertyId.name, "Nombre")
    setColumnHeader(TablePropertyId.surname, "Apellido")
    setColumnHeader(TablePropertyId.socialId, "CI")

    selectionMode = SelectionMode.Single

    p.addItemClickListener(new ItemClickListener (){
      override def itemClick(event: ItemClickEvent) = {

        if (event.isDoubleClick)
//          println(s"getItem ${event.getItem}")
          println(s"getItemId ${event.getItemId}") // 1
//          println(s"getPropertyId ${event.getPropertyId}") // idSurname
          itemTableId = Some(event.getItemId.asInstanceOf[Int])
          println(itemTableId)
          tabSheet.selectedTab = editionTab
      }
    })
  }

  private val allEmployersTab = tabSheet.addTab(new VerticalLayout { vl =>
      caption = "*"
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
        val searchButton:Button= addComponent(new Button{
          caption = "Buscar"
          addStyleName("default")
          clickListeners +=  searchButtonEvent2()
        })

        private val clearButton:Button= addComponent(new Button{
          caption = "Limpiar"
          addStyleName("default")
          clickListeners += (_ => table.removeAllItems())
        })

        hl.setAlignment(searchButton, Alignment.BottomCenter)
        hl.setAlignment(clearButton, Alignment.BottomCenter)
      })

      addComponent(new VerticalLayout{ vl =>
        spacing = true
        margin = true
        addComponent(table)
      })
    })

  private val editionTab = tabSheet.addTab(new VerticalLayout { vl =>
      caption = "Editar"
      addComponent(new VerticalLayout{ vl =>
        setSizeFull()
        spacing = true
        margin = true
        addComponent(nameTextField)
        addComponent(surnameTextField)
        addComponent(socialIdTextField)
      })
    })

  tabSheet.selectedTabChangeListeners.add(e => {
    if (e.tabSheet.selectedTab == editionTab) {
      if (itemTableId.isDefined){
        table.getItemOption(itemTableId.get).foreach{ i =>
          nameTextField.value = i.getProperty(TablePropertyId.name).value
          surnameTextField.value = i.getProperty(TablePropertyId.surname).value
          socialIdTextField.value = i.getProperty(TablePropertyId.socialId).value
        }
      }
    }
  })


  setSizeFull()
  addStyleName("dashboard-view")
  addComponent(tabSheet.p)

  // implementation using futures does not refresh table correctly
  def searchButtonEvent(e: Button.ClickEvent): Unit = {
    table.removeAllItems()
    table.selectionMode = SelectionMode.None
    dao.Employees.getEmployees.map(_.zipWithIndex.map {case (employ,i) =>
      (i, List(
        TablePropertyId.name -> employ.name,
        TablePropertyId.surname -> employ.surname,
        TablePropertyId.socialId -> employ.socialId
      ))
    }).map { res =>
      table.container = Container.apply(res: _*)
    }
  }

  /**
    * zipwithindex 0 to 19
    * TODO this i of  zipwithindex  should be same of the cast
    */
  def searchButtonEvent2(): Unit = {
    table.removeAllItems()
    table.selectionMode = SelectionMode.None
    val res = dao.Employees.getEmployees2.zipWithIndex.map({case (employ,i) =>
      (i, List(
          TablePropertyId.name -> employ.name,
          TablePropertyId.surname -> employ.surname,
          TablePropertyId.socialId -> employ.socialId
        )
      )
    })
    table.container = Container(res: _*)
  }

  def clearButtonEvent(e: Button.ClickEvent): Unit = {
    table.removeAllItems()
   }

  def enter(event: ViewChangeEvent) : Unit = {

  }

}