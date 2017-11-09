package com.virtualcodemonkeys.mazes

import scala.util.Random

object Tester {

  def main(args: Array[String]): Unit = {
    println("Tester starting...")

    val grid = new Grid(15, 15)
//    println(grid.printGridWithCellNumbers())
//    println("  ")
    println(grid.printGrid())

  }


  def printHelp(): Unit = {
    println("This is the help display")
  }

}