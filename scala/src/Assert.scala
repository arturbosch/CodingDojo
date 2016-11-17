/**
  * @author Artur Bosch
  */
object Assert {

  def main(args: Array[String]): Unit = {
    5 matches ""
    5 matches 10
  }

  implicit class AssertExt(any: Any) {
    def matches(except: Any) {
      if (any == except)
        println("SUCCESS")
      else
        println(s"FAIL: '$any' does not match '$except'")
    }
  }

}

