package com.virtualcodemonkeys.mazes

import scala.collection.mutable.ListBuffer

case class Grid(rows: Int, columns: Int) {
  val rowCount = rows
  val columnCount = columns
  private val cellsList = scala.List()
  var gridBuffer = new ListBuffer[ListBuffer[Cell]]()
  gridBuffer = prepareGrid()
  configureCells()

  def prepareGrid(): ListBuffer[ListBuffer[Cell]] = {
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

  def configureCells() :Unit ={

  }

  def getCell(row: Int, col: Int) :Option[Cell] = {
    println("getCell( %s, %s )".format(row,col))
    if (row < 0 || col < 0) Option.empty
    else if (row > rowCount - 1) Option.empty
    else if (col > columnCount - 1) Option.empty
    else
      return Option.apply( gridBuffer.toList(row).toList(col) )
  }

  def newCell(row: Int, col: Int): Cell = {
    new Cell(row,col)
  }

  def printGrid(): String = {
    println("> Entering printGrid() method")

    var out = new StringBuilder
    out ++= "+" + "---+" * columnCount + "\n"

    gridBuffer.toList.foreach(row=>{
      out ++= printRow(row.toList)
    })
    out.toString()
  }

  def printRow(row: List[Cell]): String = {
    var middle = new StringBuilder
    var bottom = new StringBuilder
    middle ++= "|"
    bottom ++= "+"
    row.foreach(cell=>{
      middle ++= "%d,%d|".format(cell.row,cell.column)
      bottom ++= "---+"
    })
    "%s\n%s\n".format(middle, bottom)
  }


  def isLinked(c: Cell): Boolean = {
    false
  }

}

