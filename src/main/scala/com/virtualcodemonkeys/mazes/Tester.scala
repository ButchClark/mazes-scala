package com.virtualcodemonkeys.mazes

object Tester {

  def main(args: Array[String]): Unit = {
    println("Tester starting...")

    println("---------- Binary Grid -------------------")
    val grid = new BinaryGrid(15, 15)
    println(grid.printGrid())

    //
    println("---------- Sidewinder Grid -------------------")
    val sgrid = new SidewinderGrid(10,10)
    println(sgrid.printGrid())
  }

  def printHelp(): Unit = {
    println("This is the help display")
  }

}