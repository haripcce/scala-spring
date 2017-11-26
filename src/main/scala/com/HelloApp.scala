package com

import com.dao.CustomerDao
import com.entities.Customer
import com.example.services.HelloService
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.{CommandLineRunner, SpringApplication}
import org.springframework.context.ApplicationContext

@SpringBootApplication
class HelloApp extends CommandLineRunner {
  val logger = Logger getLogger classOf[HelloApp]
  @Autowired
  var helloSvc: HelloService = _
  @Autowired
  var applicationContext: ApplicationContext = _
  @Autowired
  var customerDao: CustomerDao = _
  @Value("${titlemsg}")
  var titleMsg: String = _

  override def run(args: String*): Unit = {
    println("Hello from Command line runner!")
    println(helloSvc.hello())
    println("titleMsg :" + titleMsg)
    logger info "titleMsg :" + titleMsg;

    val dao: CustomerDao = applicationContext getBean classOf[CustomerDao]

    customerDao.save(new Customer("Paul", "Hildebrand"))
    val customer = customerDao.find(1)
    println(customer.get)
    val list = customerDao.getAll
println(list.foreach(customer=> println(customer.firstName)))
  }
}


object Hello {
  def main(args: Array[String]): Unit = {
    SpringApplication run classOf[HelloApp]
  }
}
