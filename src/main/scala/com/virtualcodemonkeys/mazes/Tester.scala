package com.virtualcodemonkeys.mazes

object Tester {

  def main(args: Array[String]): Unit = {
    println("Tester starting...")

    val grid = new Grid(5, 9)
    grid.prepareGrid()

    println("getCell(2,1): %s".format(grid.getCell(2,1).toString))
    println("getCell(2,111): %s".format(grid.getCell(2,111).toString))
    println("getCell(222,0): %s".format(grid.getCell(222,0).toString))

    println(grid.printGrid())
  }


  def printHelp(): Unit = {
    println("This is the help display")
  }

}