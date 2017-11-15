package com.virtualcodemonkeys.mazes

import scala.collection.mutable.ListBuffer
import scala.util.Random

abstract class Grid(rows: Int, columns: Int) {

  val rowCount = rows
  val columnCount = columns
  private val cellsList = scala.List()
  var gridBuffer = new ListBuffer[ListBuffer[Cell]]()
  gridBuffer = prepareGrid()
  configureCells()

  def configureCells() :Unit

  def configureCell(rand: Random, cell: Cell) :Unit

  def prepareGrid(): ListBuffer[ListBuffer[Cell]] = {
    println(">>>  prepareGrid()")
    val grid = new ListBuffer[ListBuffer[Cell]]()

    for (r <- 0 to (rowCount - 1)) {

      val cells = new ListBuffer[Cell]()
      for (c <- 0 to (columnCount - 1)) {
        cells += newCell(r, c)
      }
      grid.+=(cells)
    }

    grid
  }

  def getCell(row: Int, col: Int): Option[Cell] = {
    //    println("getCell( %s, %s )".format(row, col))
    if (row < 0 || col < 0) None
    else if (row > rowCount - 1) None
    else if (col > columnCount - 1) None
    else
      return Option.apply(gridBuffer.toList(row).toList(col))
  }

  def newCell(row: Int, col: Int): Cell = {
    new Cell(row, col)
  }

  def printGrid(): String = {
//    println("> Entering printGrid() method")

    var out = new StringBuilder
    out ++= "+" + "---+" * columnCount + "\n"

    gridBuffer.toList.foreach(row => {
      out ++= printRow(row.toList)
    })
    out.toString()
  }

  def printRow(row: List[Cell]): String = {
    var middle = new StringBuilder
    var bottom = new StringBuilder
    middle ++= "|"
    bottom ++= "+"
    row.foreach(cell => {
      if (cell.linked_east) middle ++= "    "
      else middle ++= "   |"

      if (cell.linked_south) bottom ++= "   +"
      else bottom ++= "---+"
    })
    "%s\n%s\n".format(middle, bottom)
  }

  def printGridWithCellNumbers(): String = {
//    println("> Entering printGridWithCellNumbers() method")

    var out = new StringBuilder
    out ++= "+" + "---+" * columnCount + "\n"

    gridBuffer.toList.foreach(row => {
      out ++= printRowsWithCellNumbers(row.toList)
    })
    out.toString()
  }

  def printRowsWithCellNumbers(row: List[Cell]): String = {
    var middle = new StringBuilder
    var bottom = new StringBuilder
    middle ++= "|"
    bottom ++= "+"
    row.foreach(cell => {
      middle ++= "%d,%d|".format(cell.row, cell.column)
      bottom ++= "---+"
    })
    "%s\n%s\n".format(middle, bottom)
  }

}

