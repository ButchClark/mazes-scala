import com.virtualcodemonkeys.mazes.{BinaryGrid, Grid}
import org.scalatest.FunSpec

class GridFunTest extends FunSpec{

  describe("A Grid"){

    describe("when initialized"){
      val grid = new BinaryGrid(3,4)

      it("should have rowCount"){
        assert(grid.rowCount == 3)
      }

      it("should have columnCount"){
        assert(grid.columnCount == 4)
      }

      it("should return a Cell"){
        val cell = grid.getCell(1,1)
        assert(cell.get.row == 1)
        assert(cell.get.column == 1)
      }

      it("should return nothing if row out of range-high"){
        val cell = grid.getCell(9,1)
        assert(cell == None)
      }
      it("should return nothing if row out of range-low"){
        val cell = grid.getCell(-3,1)
        assert(cell == None)
      }

      it("should return nothing if column out of range-high"){
        val cell = grid.getCell(1,9)
        assert(cell == None)
      }
      it("should return nothing if column out of range-low"){
        val cell = grid.getCell(1,-3)
        assert(cell == None)
      }


    }

  }
}
