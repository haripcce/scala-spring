package com.dao.impl

import javax.persistence.EntityManager
import scala.collection.JavaConversions._
import com.dao.CustomerDao
import com.entities.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.{Propagation, Transactional}

@Repository("customerDao")
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
class CustomerDaoImpl extends CustomerDao {
  @Autowired
  var entityManager: EntityManager = _

  override def save(customer: Customer): Unit = customer.id match {
    case 0 => entityManager.persist(customer)
    case _ => entityManager.merge(customer)
  }
  def find(id: Int): Option[Customer] = {
    Option(entityManager.find(classOf[Customer], id))
  }

  def getAll: List[Customer] = {
    entityManager.createQuery("From Customer", classOf[Customer]).getResultList.toList
  }

  def getByLastName(lastName : String): List[Customer] = {
    entityManager.createQuery("From Customer Where lastName = :lastName", classOf[Customer]).setParameter("lastName", lastName).getResultList.toList
  }
}
