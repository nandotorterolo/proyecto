package com.vsf.dashboardImpl.dao.firebase

import java.io.{File, FileInputStream}

import com.vsf.dashboardImpl.model.Empleado
import com.vsf.dashboardImpl.dao.firebase.JsonImplicit._

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

//import com.google.firebase.database._
//import com.google.firebase.{FirebaseApp, FirebaseOptions}
import com.seancheatham.storage.firebase.FirebaseDatabase
import scala.concurrent.ExecutionContext.Implicits.global

import scala.io.Source

/**
  * Created by user on 5/1/17.
  * https://firebase.google.com/docs/admin/setup
  *
  *
//  *var config = {
//    *apiKey: "AIzaSyDA1B-kdC8iIm5zySPZpxbXnDhp4SsZdds",
//    *authDomain: "proyecto-858b4.firebaseapp.com",
//    *databaseURL: "https://proyecto-858b4.firebaseio.com",
//    *projectId: "proyecto-858b4",
//    *storageBucket: "proyecto-858b4.appspot.com",
//    *messagingSenderId: "484781699495"
//  *};
//  *firebase.initializeApp(config);
  */
//https://github.com/SeanCheatham/scala-storage/blob/master/README.md
object Test extends App{

////  private val file2 = new File("firebaseServiceAccountKey.json")
//  private val file = getClass.getResource("/firebaseServiceAccountKey.json")
//  private val serviceAccount = new FileInputStream(file.getFile)
////  val lines = Source.fromInputStream(serviceAccount).getLines
////  lines.foreach(line => println(line))
//  private val databaseUrl = "https://proyecto-858b4.firebaseio.com"
//  private val options = new FirebaseOptions.Builder().setServiceAccount(serviceAccount).setDatabaseUrl(databaseUrl).build()
//  private val firebaseApp = FirebaseApp.initializeApp(options)
//
//  // As an admin, the app has access to read and write all data, regardless of Security Rules
//  private val database = FirebaseDatabase.getInstance()
//  private val empleadosRef = database.getReference("empleados")
//
//  empleadosRef.orderByKey().startAt("empleado_id").addChildEventListener(new ChildEventListener {
//    override def onChildRemoved(dataSnapshot: DataSnapshot) = ???
//
//    override def onChildMoved(dataSnapshot: DataSnapshot, s: String) = ???
//
//    override def onChildChanged(dataSnapshot: DataSnapshot, s: String) = {
//      println(dataSnapshot.getKey)
//    }
//
//    override def onCancelled(databaseError: DatabaseError) = ???
//
//    override def onChildAdded(dataSnapshot: DataSnapshot, s: String) = {
//      println(dataSnapshot.getKey)
//    }
//  })
//
//  Thread.sleep(15)

// la forma que esta arriba es usando la libreria pura, la de abajo es usando el wrapper. (mucho mas comodo, no se si aceptara filtros)


  private val file = getClass.getResource("/firebaseServiceAccountKey.json")
  private val serviceAccount = new FileInputStream(file.getFile)
  val db: FirebaseDatabase = FirebaseDatabase.fromServiceAccountKey(serviceAccount, "https://proyecto-858b4.firebaseio.com")




  // References /empleados/empleado_id_1/nombre
//  val readFuture: Future[String] = db.get("empleados", "empleado_id_1", "nombre").map(_.as[String])

//  readFuture.map { res => println(s"El resultado fue -> $res");  }  // Fernando



//  val res2 = db.getCollection("empleados").map( { res =>
//    res.foreach(println)
//
//  })

  val res = db.getCollection("empleados").map(_.flatMap(_.validate[Empleado].asOpt).toList)
  res.map(println)


//  val json: JsValue = Json.obj(
//    "nombre" -> "fer",
//    "apellido" -> "tort",
//    "roles" -> Json.obj("sup" -> 1, "sup2" -> 1)
//  )


//  println(Json.stringify(json))
////  println(Json.stringify(json2))
//
//  json.validate[Empleado] match {
//    case JsSuccess(v,p) => println(s"$v")
//    case JsError(s) => println(s" error $s")
//  }

  Await.result(res, Duration.Inf)


  /**
    * El resultado fue -> Fernando
    *
      El resultado fue -> {"apellido":"Torterolo","roles":{"supervisores":true},"nombre":"Fernando"}
      El resultado fue -> {"apellido":"Perez","roles":{"operadores":true},"nombre":"Juan"}
    */

}



