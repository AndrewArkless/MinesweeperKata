import org.scalatest.{FlatSpec, Matchers, WordSpecLike, _}
import org.scalatest.prop.TableDrivenPropertyChecks._

import scala.io.Source

class MineSweeperSpec extends WordSpecLike with Matchers {
  import scala.io.Source._
  val singleCell=Table(
    ("Minefield","RevealledMinefield"),
    ("-\n",           "0\n"),
    ("*\n",           "*\n")
  )

  val singleRow=Table(
    ("Minefield","RevealledMinefield"),
    ("--\n",           "00\n"),
    ("**\n",            "**\n"),
    ("*-\n",           "*1\n"),
    ("-*\n",           "1*\n"),
    ("*-------*\n",    "*1000001*\n"),
    ("*---*---*\n",    "*101*101*\n")

  )

  val singleColumn=Table(
    ("Minefield","RevealledMinefield"),
    ("-\n" +
     "-\n",

     "0\n" +
     "0\n"
    ),

    ("*\n" +
     "*\n",

     "*\n" +
     "*\n"
    ),


    ("*\n" +
      "-\n",

     "*\n" +
     "1\n"),

    ("-\n" +
     "*\n",

     "1\n" +
     "*\n"
    ),


    ("*\n" +
     "-\n" +
     "-\n" +
     "-\n" +
     "-\n" +
     "-\n" +
     "-\n" +
     "-\n" +
     "*\n",

     "*\n" +
     "1\n" +
     "0\n" +
     "0\n" +
     "0\n" +
     "0\n" +
     "0\n" +
     "1\n" +
     "*\n"
    ),

    ("*\n" +
     "-\n" +
     "-\n" +
     "-\n" +
     "*\n" +
     "-\n" +
     "-\n" +
     "-\n" +
     "*\n",

      "*\n" +
      "1\n" +
      "0\n" +
      "1\n" +
      "*\n" +
      "1\n" +
      "0\n" +
      "1\n" +
      "*\n")
   )

  val regularMinefield=Table(
    ("Minefield","RevealledMinefield"),
    ("----\n" +
     "----\n" +
     "----\n" +
     "----\n" +
     "----\n",

     "0000\n" +
     "0000\n" +
     "0000\n" +
     "0000\n" +
     "0000\n"
    ),

    ("****\n" +
     "****\n" +
     "****\n" +
     "****\n" +
     "****\n",

     "****\n" +
     "****\n" +
     "****\n" +
     "****\n" +
     "****\n"
    ),

    ("*----\n"  +
     "*----\n" +
     "*----\n" +
     "*----\n" +
     "*----\n",

     "*2000\n" +
     "*3000\n" +
     "*3000\n" +
     "*3000\n" +
     "*2000\n"),

    ("----*\n" +
     "----*\n" +
     "----*\n" +
     "----*\n" +
     "----*\n",

     "0002*\n" +
     "0003*\n" +
     "0003*\n" +
     "0003*\n" +
     "0002*\n"
    ),

    ("*----\n" +
     "-----\n" +
     "-----\n" +
     "-----\n" +
     "-----\n",

     "*1000\n" +
     "11000\n" +
     "00000\n" +
     "00000\n" +
     "00000\n"),

    ("*---*\n" +
     "-----\n" +
     "-----\n" +
     "-----\n" +
     "*---*\n",

     "*101*\n" +
     "11011\n" +
     "00000\n" +
     "11011\n" +
     "*101*\n"
    ),

    ("*---*\n" +
     "-----\n" +
     "--*--\n" +
     "-----\n" +
     "*---*\n",

     "*101*\n" +
     "12121\n" +
     "01*10\n" +
     "12121\n" +
     "*101*\n"),

    ("*****\n" +
     "*---*\n" +
     "*---*\n" +
     "*---*\n" +
     "*****\n",
     "*****\n" +

     "*535*\n" +
     "*303*\n" +
     "*535*\n" +
     "*****\n"),

    ("-----\n" +
     "-*-*-\n" +
     "*-*-*\n" +
     "-*-*-\n" +
     "-----\n",

     "11211\n" +
     "2*3*2\n" +
     "*4*4*\n" +
     "2*3*2\n" +
     "11211\n"),

    ("*****\n" +
     "-----\n" +
     "-----\n" +
     "-----\n" +
     "*****\n",

     "*****\n" +
     "23332\n" +
     "00000\n" +
     "23332\n" +
     "*****\n"
    ),

    ("-----\n" +
     "**---\n" +
     "-----\n" +
     "--*--\n" +
     "-----\n",

     "22100\n" +
     "**100\n" +
     "23210\n" +
     "01*10\n" +
     "01110\n"
    ),

    ("*--------*\n" +
     "---*-*----\n" +
     "*---------\n",

     "*11121101*\n" +
     "221*2*1011\n" +
     "*111211000\n"
    )
  )
  "Calling Minesweeper" should {
    forAll(singleCell) { (minefield: String, revealledMineField) =>
      s"return $revealledMineField where minefield is $minefield" in {
        val result = MineSweeper.revealMines(minefield)
        result shouldBe revealledMineField
      }
    }

    forAll(singleRow) { (minefield: String, revealledMineField) =>
      s"return $revealledMineField where minefield is $minefield" in {
        val result = MineSweeper.revealMines(minefield)
        println(minefield + "\n SHOULD BE \n\n" + revealledMineField )
        println("____________________________________________________ \n")
        result shouldBe revealledMineField
      }
    }

    forAll(singleColumn) { (minefield: String, revealledMineField) =>
      s"return $revealledMineField where minefield is $minefield" in {
        val result = MineSweeper.revealMines(minefield)
        println(minefield + "\n SHOULD BE \n\n" + revealledMineField )
        println("____________________________________________________ \n")
        result shouldBe revealledMineField
      }
    }

    forAll(regularMinefield){(minefield:String,revealledMineField)=>
       s"return $revealledMineField where minefield is $minefield" in {
          val result=MineSweeper.revealMines(minefield)
          println(minefield + "\n SHOULD BE \n\n" + revealledMineField )
          println("____________________________________________________ \n")
          result shouldBe revealledMineField
       }
    }

//    "read file " in {
//      import scala.io.Source._
//      val filename=Source.fromFile(filename).getLines
//        "/home/andrew/Applications/hmrc-development-environment/hmrc/minesweeper/test/testData/a.txt"
//      val testInput=
//        true shouldBe true
//    }
  }
}
