package com.vsf.dashboardImpl.ui.views

import com.vaadin.data.util.{BeanItemContainer, IndexedContainer}
import com.vaadin.event.ItemClickEvent
import com.vaadin.event.ItemClickEvent.ItemClickListener
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent
import com.vaadin.ui.Button.{ClickEvent, ClickListener}
import com.vaadin.ui._
import com.vsf.dashboardImpl.model.{Empleado, Roles}
import com.vsf.dashboardImpl.ui.tabs.{EmpladosTodosTab, EmpleadoEditarTabImpl, EmpleadoTodosTabImpl}
import com.vsf.util.ScaladinContainer

import collection.JavaConverters._
import collection.JavaConversions._
import scala.concurrent.ExecutionContext.Implicits.global

class EmployeesView() extends VerticalLayout with View
{
  val dao = com.vsf.dashboardImpl.ui.DashboardApplication.dao

  val empleadoEditarTabImpl = new EmpleadoEditarTabImpl
  val empleadosTodosTabImpl = new EmpleadoTodosTabImpl


  var itemTableId: Option[Int] = None

  private val tabSheet = new TabSheet()
  tabSheet.setCaption("Empleados")
  tabSheet.setSizeFull()


  private val grid = new Grid("Empleados")
  grid.setSizeFull()

  private val table = new Table()

  table.addContainerProperty(TablePropertyId.name, classOf[String], "")
  table.addContainerProperty(TablePropertyId.surname, classOf[String], "")
  table.addContainerProperty(TablePropertyId.socialId, classOf[String], "")
  table.setColumnHeader(TablePropertyId.name, "Nombre")
  table.setColumnHeader(TablePropertyId.surname, "Apellido")
  table.setColumnHeader(TablePropertyId.socialId, "CI")

  table.addItemClickListener(new ItemClickListener (){
      override def itemClick(event: ItemClickEvent) = {

        if (event.isDoubleClick)
//          println(s"getItem ${event.getItem}")
          println(s"getItemId ${event.getItemId}") // 1
//          println(s"getPropertyId ${event.getPropertyId}") // idSurname
          itemTableId = Some(event.getItemId.asInstanceOf[Int])
          println(itemTableId)
//          tabSheet.setSelectedTab(editionTab)
      }
    })

  val searchButton = new Button("Buscar", new ClickListener {
    override def buttonClick(event: ClickEvent) = searchButtonEvent()
  })

  val vl = new VerticalLayout()
  vl.setMargin(true)
  vl.setSpacing(true)
  vl.addComponent(searchButton)
  vl.addComponent(grid)

  tabSheet.addTab(vl, "*")
  tabSheet.addTab(empleadosTodosTabImpl, "*")
  tabSheet.addTab(empleadoEditarTabImpl, "Editar")

//  private val editionTab = tabSheet.addTab(new FormLayout { fl =>
//      caption = "Editar"
//      addComponent(new VerticalLayout{ vl =>
//        setSizeFull()
//        spacing = true
//        margin = true
//        addComponent(nameTextField)
//        addComponent(surnameTextField)
//        addComponent(socialIdTextField)
//      })
//    })
//
//  tabSheet.selectedTabChangeListeners.add(e => {
//    if (e.tabSheet.selectedTab == editionTab) {
//      if (itemTableId.isDefined){
//        table.getItemOption(itemTableId.get).foreach{ i =>
//          nameTextField.value = i.getProperty(TablePropertyId.name).value
//          surnameTextField.value = i.getProperty(TablePropertyId.surname).value
//          socialIdTextField.value = i.getProperty(TablePropertyId.socialId).value
//        }
//      }
//    }
//  })


  addStyleName("dashboard-view")
  addComponent(tabSheet)


  // implementation using futures does not refresh table correctly
  def searchButtonEvent(): Unit = {

//    val data = (1, List(
//      TablePropertyId.name -> "employ.name",
//      TablePropertyId.surname -> "employ.surname",
//      TablePropertyId.socialId -> "!"
//    ))
//    grid.setContainerDataSource(ScaladinContainer(data))

    getUI.access({ new Runnable {

      override def run():Unit = {
        // todo add a recover
        dao.getAllEmployees.map(_.zipWithIndex.map { case (employ, i) =>
          (i,
            List( TablePropertyId.name -> employ.nombre,
                  TablePropertyId.surname -> employ.apellido
                  //  TablePropertyId.socialId -> employ.socialId
            )
          )
        }).map { items =>
          grid.setContainerDataSource(ScaladinContainer(items: _*))
          getUI.push()

        }
      }
    }})

  }

//    dao.Employees.getEmployees.map(_.zipWithIndex.map {case (employ,i) =>
//      (i, List(
//        TablePropertyId.name -> employ.name,
//        TablePropertyId.surname -> employ.surname,
//        TablePropertyId.socialId -> employ.socialId
//      ))
//    }).map { res =>
//      table.container = Container.apply(res: _*)
//    }


  /**
    * zipwithindex 0 to 19
    * TODO this i of  zipwithindex  should be same of the cast
    */
//  def searchButtonEvent2(): Unit = {
//    table.removeAllItems()
//    table.selectionMode = SelectionMode.None
//    val res = dao.Employees.getEmployees2.zipWithIndex.map({case (employ,i) =>
//      (i, List(
//          TablePropertyId.name -> employ.name,
//          TablePropertyId.surname -> employ.surname,
//          TablePropertyId.socialId -> employ.socialId
//        )
//      )
//    })
//    table.container = Container(res: _*)
//  }

  def clearButtonEvent(e: Button.ClickEvent): Unit = {
//    table.removeAllItems()
   }

  def enter(event: ViewChangeEvent) : Unit = {

  }

}