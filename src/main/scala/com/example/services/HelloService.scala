package com.example.services

import org.springframework.stereotype.Service

@Service
class HelloService {
  def hello(): String = {
    "Hello from HelloService!"
  }
}
