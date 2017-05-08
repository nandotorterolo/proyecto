package com.vsf.util

import com.vaadin.data.Property
import com.vaadin.data.util.IndexedContainer

/**
  * Created by user on 5/7/17.
  */
object ScaladinContainer {

  def apply(items: (Any, Seq[(Any, Any)])*): IndexedContainer = fill(new IndexedContainer, items: _*)

  def fill(container: IndexedContainer, items: (Any, Seq[(Any, Any)])*): IndexedContainer = {
    for (item <- items) {
      val containerItem = container.addItem(item._1)

      for (property <- item._2) {
        container.addContainerProperty(property._1, property._2.getClass, null)
        val p = containerItem.getItemProperty(property._1).asInstanceOf[Property[Any]]
        p.setValue(property._2)
      }
    }
    container
  }
}
