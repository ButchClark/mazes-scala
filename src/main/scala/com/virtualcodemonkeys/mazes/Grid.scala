package com.virtualcodemonkeys.mazes

import scala.collection.mutable.ListBuffer
import scala.util.Random

case class Grid(rows: Int, columns: Int) {

  val rowCount = rows
  val columnCount = columns
  private val cellsList = scala.List()
  var gridBuffer = new ListBuffer[ListBuffer[Cell]]()
  gridBuffer = prepareGrid()
  configureCells()

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

  def configureCells(): Unit = {
    println(">>>  Starting configureCells()")
    val rand = Random
    gridBuffer.toList.reverse.toStream
      .foreach(row => {
        row.toList.toStream
          .foreach(cell => configureCell(rand, cell))
      })

    println(">>>  Done configureCells()")
  }

  def configureCell(rand: Random, cell: Cell): Unit = {

    // is it the bottom, right cell?
    // another bottom row cell?
    // another right column cell?
    // Other
    if (cell.row >= rowCount - 1 && cell.column >= columnCount - 1) {
      // no links from here
      cell.linked_east = false;
      cell.linked_south = false;
      // link cell to the west and north
      getCell(cell.row, cell.column - 1).get.linked_east = true
      getCell(cell.row - 1, cell.column).get.linked_south = true

    } else if (cell.row >= rowCount - 1) {
      // can only link east
      cell.linked_east = true;
      // link cell to the west
      getCell(cell.row, cell.column + 1).get.linked_west = true

    } else if (cell.column >= columnCount - 1) {
      // can only link north
      cell.linked_north = true;
      // link cell to the north
      if( getCell(cell.row - 1, cell.column) != None)
        getCell(cell.row - 1, cell.column).get.linked_south = true

    } else {
      if (rand.nextInt(100) >= 50) {
        cell.linked_east = true
        println("Setting EAST link for cell( " + cell.row + ", " + cell.column + " )")
        if (getCell(cell.row, cell.column + 1) != None)
          getCell(cell.row, cell.column + 1).get.linked_west = true
      } else {
        cell.linked_south = true
        println("Setting SOUTH link for cell( " + cell.row + ", " + cell.column + " )")
        if (getCell(cell.row + 1, cell.column) != None)
          getCell(cell.row + 1, cell.column).get.linked_north = true
      }
    }

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
    println("> Entering printGrid() method")

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
    println("> Entering printGridWithCellNumbers() method")

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

