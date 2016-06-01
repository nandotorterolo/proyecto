package com.ftorterolo.SemDashboard.ui.views

import java.util.concurrent.TimeUnit

import com.ftorterolo.SemDasboard.views.RootGridLayoutBase
import com.ftorterolo.config._
import com.vaadin.event.LayoutEvents.{LayoutClickEvent, LayoutClickListener}
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent
import com.vaadin.navigator.{ViewChangeListener, View}
import com.vaadin.shared.ui.MarginInfo
import com.vaadin.ui.Button.{ClickEvent, ClickListener}
import com.vaadin.ui.Layout.AlignmentHandler
import com.vaadin.ui._
import org.slf4j.LoggerFactory

class CorredorView extends VerticalLayout with View  {

  val logger = LoggerFactory.getLogger(this.getClass)

  val detector_001 = new Detector_001
  val detector_002 = new Detector_002
  val detector_003 = new Detector_003
  val detector_004 = new Detector_004
  val detector_005 = new Detector_005

  val startButton : Button = {
    val but = new Button("start")
    but.addClickListener( new ClickListener {
      override def buttonClick(event: ClickEvent): Unit = {
//        MessageHandler.purge()
        detector_001.start(initialDelay = 10, period = 30, TimeUnit.SECONDS)
        detector_002.start(initialDelay = 10, period = 30, TimeUnit.SECONDS)
        detector_003.start(initialDelay = 10, period = 30, TimeUnit.SECONDS)
        detector_004.start(initialDelay = 10, period = 30, TimeUnit.SECONDS)
        detector_005.start(initialDelay = 10, period = 10, TimeUnit.SECONDS)  // dejar corriendo
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
//        detector_001.shutdown()
//        detector_002.shutdown()
//        detector_003.shutdown()
//        detector_004.shutdown()
        startButton.setEnabled(true)
        stopButton.setEnabled(false)
      }
    }
    )
    but
  }

  override def enter(event: ViewChangeEvent): Unit = Unit

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


    val vlayout = new HorizontalLayout()
    startButton.setEnabled(true)
    stopButton.setEnabled(false)
    vlayout.addComponent(startButton)
    vlayout.addComponent(stopButton)
    addComponent(vlayout)

    //    val row = getRowView
//    row.addComponent(vlayout)

    val row2 = getRowView
    val layout = new RootGridLayoutBase
    row2.addComponent(layout.getRootComponent)


//    startButton.click()  // remove it.



  }
  private def getRowView : HorizontalLayout = {
    val row = new HorizontalLayout()
    row.setSizeFull()
//    row.setMargin(new MarginInfo(true, true, false, true))
//    row.setSpacing(true)
    addComponent(row)
    setExpandRatio(row, 1.5f)
    row
  }
}
