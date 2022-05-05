object Main extends App {
  // A class to represent the values of command-line options
  case class Config(lines: String)

  import scopt.OParser

  val myBuilder = OParser.builder[Config]

  val myParser = {
    import myBuilder._
    OParser.sequence(
      programName("mateusz"),
      head("Blabla", "1.0.0-alpha"),
      help("help").text("prints this usage text"),
      opt[String]('l', "lines")
        .action((x, cfg) => cfg.copy(lines = x))
        .text("My name property"),
    )
  }

  val config = OParser.parse(
    myParser,
    args,
    Config(lines = "")).getOrElse {
    println("Not enough or incorrect command-line arguments. Exiting...")
    sys.exit(-1)
  }

  println(s"${config.lines}")
}