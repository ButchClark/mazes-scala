package com.virtualcodemonkeys.mazes

import scala.collection.mutable.ListBuffer

case class Cell(rowIn: Int, columnIn: Int){
  private val linksList = new ListBuffer[Cell]()
  val row = rowIn
  val column = columnIn

  def links() :List[Cell] =
    linksList.toList

  def populateLinks(): Unit = {
    // Here we set the neighbor links
  }
}



