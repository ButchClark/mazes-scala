
package com.virtualcodemonkeys.mazes

import scala.util.Random

class BinaryGrid(rows: Int, columns: Int) extends Grid(rows,columns) {

  override def configureCells(): Unit = {
    println(">>>  Starting BinaryGrid.configureCells()")
    val rand = Random
    gridBuffer.toList.reverse.toStream
      .foreach(row => {
        row.toList.toStream
          .foreach(cell => configureCell(rand, cell))
      })

    println(">>>  Done BinaryGrid.configureCells()")
  }

  override def configureCell(rand: Random, cell: Cell): Unit = {

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
      if (getCell(cell.row - 1, cell.column) != None)
        getCell(cell.row - 1, cell.column).get.linked_south = true

    } else {
      if (rand.nextInt(100) >= 50) {
        cell.linked_east = true
        //        println("Setting EAST link for cell( " + cell.row + ", " + cell.column + " )")
        if (getCell(cell.row, cell.column + 1) != None)
          getCell(cell.row, cell.column + 1).get.linked_west = true
      } else {
        cell.linked_south = true
        //        println("Setting SOUTH link for cell( " + cell.row + ", " + cell.column + " )")
        if (getCell(cell.row + 1, cell.column) != None)
          getCell(cell.row + 1, cell.column).get.linked_north = true
      }
    }
  }

}

