import com.virtualcodemonkeys.mazes.Grid
import org.scalatest.{FlatSpec, Matchers}

class GridTest extends FlatSpec with Matchers{

  "A Grid" should "accept row and column sizes" in {
    val grid = new Grid(3,4)

    grid.rowCount should be (3)
    grid.columnCount should be (4)
  }


}
