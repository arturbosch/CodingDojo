import java.io.Closeable

/**
  * @author Artur Bosch
  */
object Resource {
  def using[R <: Closeable, T](stream: R)(f: R => T): T =
    try {
      f(stream)
    } finally {
      stream.close()
    }
}
