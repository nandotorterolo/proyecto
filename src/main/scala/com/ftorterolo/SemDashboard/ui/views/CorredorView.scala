package com.ftorterolo.SemDashboard.ui.views

import java.util.concurrent.TimeUnit

import com.ftorterolo.config.CorredorService
import com.vaadin.event.LayoutEvents.{LayoutClickEvent, LayoutClickListener}
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent
import com.vaadin.navigator.{ViewChangeListener, View}
import com.vaadin.shared.ui.MarginInfo
import com.vaadin.ui.Button.{ClickEvent, ClickListener}
import com.vaadin.ui._
import org.slf4j.LoggerFactory

class CorredorView extends VerticalLayout with View  {

  val logger = LoggerFactory.getLogger(this.getClass)

  override def enter(event: ViewChangeEvent): Unit = Unit

  val semaforosService = new CorredorService


  val startButton : Button = {
    val but = new Button("start")
    but.addClickListener( new ClickListener {
      override def buttonClick(event: ClickEvent): Unit = {
        semaforosService.start(3, 3, TimeUnit.SECONDS)
        startButton.setEnabled(false)
        stopButton.setEnabled(true)
      }
    })
    but
  }

  val stopButton : Button = {
    val but = new Button("stop")
    but.addClickListener( new ClickListener {
      override def buttonClick(event: ClickEvent): Unit = {
        semaforosService.shutdown()
        startButton.setEnabled(true)
        stopButton.setEnabled(false)
      }
    }
    )
    but
  }

  init()

  def init() {
    setSizeFull()
    addStyleName("dashboard-view")

    val top = new HorizontalLayout()
    top.setWidth("100%")
    top.setSpacing(true)
    top.addStyleName("toolbar")
    addComponent(top)

    val title = new Label("Corredor Garzon")
    title.setSizeUndefined()
    title.addStyleName("h1")
    top.addComponent(title)
    top.setComponentAlignment(title, Alignment.MIDDLE_LEFT)
    top.setExpandRatio(title, 1)


    val vlayout = new VerticalLayout()
    startButton.setEnabled(true)
    stopButton.setEnabled(false)
    vlayout.addComponent(startButton)
    vlayout.addComponent(stopButton)

    val row = getRowView
    row.addComponent(vlayout)

  }

  private def getRowView : HorizontalLayout = {
    val row = new HorizontalLayout()
    row.setSizeFull()
    row.setMargin(new MarginInfo(true, true, false, true))
    row.setSpacing(true)
    addComponent(row)
    setExpandRatio(row, 1.5f)
    row
  }



}
