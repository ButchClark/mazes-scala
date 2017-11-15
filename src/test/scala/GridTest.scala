import com.virtualcodemonkeys.mazes.{Grid, SidewinderGrid}
import org.scalatest.{FlatSpec, Matchers}

class GridTest extends FlatSpec with Matchers{

  "A Grid" should "set row and column sizes" in {
    val grid = new SidewinderGrid(3,4)

    grid.rowCount should be (3)
    grid.columnCount should be (4)
  }
}
