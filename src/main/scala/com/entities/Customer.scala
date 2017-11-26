package com.entities

import javax.persistence._

import scala.beans.BeanProperty


@Entity
@Table(name = "customer")
class Customer(f: String, l: String) {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @BeanProperty
  var id: Int = _

  @BeanProperty
  var firstName: String = f

  @BeanProperty
  var lastName: String = l

  def this() = this(null, null)

  override def toString = id + " = " + firstName + " " + lastName
}
