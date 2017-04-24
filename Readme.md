# Proyecto de Ingeniería de Software

Tutor: 

Integrantes: Victor Cordeiro/ Sebastían / Fernando Torterolo

Aplicación central que implementa control y auditoría de los movimientos de los vigilantes mediante la lectura en puntos específicos de un código
QR validado vía GPS, o tags NFC en el caso que el recorrido interno no permita la recepción de señal GPS.

Permite la gestión de usuarios, dispositivos, rondas y cronogramas y visualización de los datos recibidos por los dispositivos móviles.

(En progreso)


## Tools 

*Vaadin* (https://vaadin.com)
    
    Build your web UIs in HTML or Java

*Scaladin*   (https://github.com/henrikerola/scaladin)
    
    It's a wrapper library that provides a pure Scala API for Vaadin Framework.
    
*xsbt-web-plugin* (https://github.com/earldouglas/xsbt-web-plugin) 

    sbt extension for building J2EE Web applications in Scala and Java.  
    
*sbt-vaadin-plugin* (https://github.com/henrikerola/sbt-vaadin-plugin)

    sbt-vaadin-plugin is a sbt plugin that makes possible to use sbt to build Vaadin projects. The plugin is for Vaadin 7 and sbt 0.13.
    
    
# Database Mysql

    mysql> CREATE DATABASE areasegura;
    Query OK, 1 row affected (0.00 sec)
    
    mysql> CREATE USER 'appareasegura'@'localhost' IDENTIFIED BY 'password';  
    Query OK, 0 rows affected (0.00 sec)
    
    mysql> GRANT ALL PRIVILEGES ON areasegura.* TO 'appareasegura'@'localhost';
    Query OK, 0 rows affected (0.00 sec)
    
    mysql> FLUSH PRIVILEGES;
    Query OK, 0 rows affected (0.00 sec)
    

# Java Messaging Service (JMS) API
is a specification that allows java programs to create, send and receive messages asynchronously from one application to another. 
This communication is loosely coupled and very much reliable.
JMS Provider is a messaging system that implements the JMS specification.
This acts as the Message Oriented Middleware (MOM), are some of the popular MOM Service Providers.

* TIBCO EMS (TIBCO)
* ActiveMQ (Apache)
* JBOSSMQ (JBOSS)
* MQSeries (IBM)

Send the message are known as the JMS Producer / Publisher
Receive the message are said to be JMS Consumer/ Subscriber.

Apache ActiveMQ advantages, most popular, powerful, open source, light weight.

- Download http://activemq.apache.org/activemq-5144-release.html
- cd [activemq_install_dir]
- tar zxvf activemq-x.x.x-bin.tar.gz
- cd apache-activemq-5.14.4/bin/
- /apache-activemq-5.14.4/bin$ ./activemq start
    INFO: Loading '/home/user/Software/apache-activemq-5.14.4//bin/env'
    INFO: Using java '/usr/bin/java'
    INFO: Starting - inspect logfiles specified in logging.properties and log4j.properties to get details
    INFO: pidfile created : '/home/user/Software/apache-activemq-5.14.4//data/activemq.pid' (pid '4830')
    
- Test installation
    URL: http://127.0.0.1:8161/admin/
    Login: admin
    Password: admin


- see port and more details 
    ps aux | grep activemq
    
    

    Punto a Punto (PTP), 
         en la que un mensaje se consume por un único consumidor. (pueden haber muchos consumidoes)
         puede haber varios emisores, 
         el destino es una cola, queue.
         los mensajes son retenidos hasta que son consumidos o expiren. receptor envia ack recepcion correcta
         
         
    Publicación/Subscripción (Pub/Sub), en la que un mensaje se consume por muchos consumidores.
    el destino es una topico, no funciona como una pila, no se encolan.
    un nuevo mensaje en eltpico, sobreescribe el existente
    cada mensaje puede tener múltiples consumidores

ver imagen para estos apuntes.
     http://www.jtech.ua.es/j2ee/publico/mens-2010-11/sesion01-apuntes.html
     

Dado estos comentarios, posible definicion de esta manera la arquitectura.

Comunicaciones desde los moviles hacia el servidor:
    Modelos PTP:
        productor/es: moviles, de 1:n
        consumidor/es: servidor, 1 
        nombre: "al_servidor"
        tipo: cola  
        objectivo: envio de alertas, chechpoints, notificaciones, otros  
        


Comunicaciones desde servidor hacia los moviles: (topicos)
    Modelos PTP:
        productor/es: servidor 1
        consumidor/es: moviles, 1:n 
        nombre: "a_los_moviles"
        tipo: topico  
        objectivo: saludos de navidad, notificacion de pago de sueldo.
          
Una sesion es un contexto monohilo para producir y consumir mensajes, 
Existen sesiones transaccionales y las no transaccionales, 
    transaccionales:  No necesita acuso de recibo pues se tratan como unidad atomica sujeto a protocolo commit/rollback
    no transaccionales: hay que seleccionar un tipo de acuse de recibo, Session.AUTO_ACKNOWLEDGE 
    
La entrega/recepcion de mensajes no comienza hasta que no se inicia la conexión creada mediante el método start.
 
    
    
    
    
    
    
    
