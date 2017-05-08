package com.vsf.dashboardImpl.ui.views

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent
import com.vaadin.tapio.googlemaps.GoogleMap
import com.vaadin.tapio.googlemaps.client.LatLon
import com.vaadin.ui.{HorizontalLayout, TabSheet, TextField, VerticalLayout}


// https://vaadin.com/directory#!addon/googlemaps-add-on
class RondasView extends VerticalLayout with View
{

  private val nameTextField = new TextField("Ronda")
  private val tabSheet = new TabSheet()

  val layout = new VerticalLayout()
  // AIzaSyAb0gBp3eQ3g5t4yZ5Kdmy6jXnFyqrulCM
  val googleMap = new GoogleMap("AIzaSyAb0gBp3eQ3g5t4yZ5Kdmy6jXnFyqrulCM", null, "english")
  googleMap.setSizeFull()
  googleMap.addMarker("DRAGGABLE: Paavo Nurmi Stadion", new LatLon(60.442423, 22.26044), true, "VAADIN/1377279006_stadium.png")
  googleMap.addMarker("DRAGGABLE: Kakolan vankila",    new LatLon(60.44291, 22.242415), true, null)
  googleMap.addMarker("NOT DRAGGABLE: Iso-Heikkilä", new LatLon(60.450403, 22.230399), false, null)
  googleMap.setMinZoom(4)
  googleMap.setMaxZoom(16)
  layout.addComponent(googleMap)

//
//
//  private val allRondasTab = tabSheet.addTab(new VerticalLayout { vl =>
//      caption = "*"
//      spacing = true
//      addStyleName("toolbar")
//
//      addComponent(layout)
//      addComponent(new HorizontalLayout { hl =>
//        setSizeFull()
//        spacing = true
//        margin = true
//
//
//      })
//    })


  setSizeFull()
  addStyleName("dashboard-view")
  addComponent(tabSheet)


  def enter(event: ViewChangeEvent) : Unit = {

  }

}