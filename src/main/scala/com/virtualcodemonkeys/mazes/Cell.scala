package com.virtualcodemonkeys.mazes

case class Cell(rowIn: Int, columnIn: Int){
  val row = rowIn
  val column = columnIn

  var linked_north = false
  var linked_south = false
  var linked_east = false
  var linked_west = false

}



