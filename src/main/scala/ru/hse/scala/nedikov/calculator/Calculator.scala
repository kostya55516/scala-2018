package ru.hse.scala.nedikov.calculator

import org.antlr.v4.runtime.{BufferedTokenStream, CharStreams}

import scala.io.StdIn.readLine

object Calculator {
  def main(args: Array[String]): Unit = {
    while (true) {
      val in = readLine()
      if (in == ":q") return
      println(evaluate(in))
    }
  }

  def evaluate(in: String): String = {
    val calculatorLexer = new CalculatorLexer(CharStreams.fromString(in))
    val exp = new CalculatorParser(new BufferedTokenStream(calculatorLexer)).eval().accept(new Visitor)
    exp match {
      case dExp: DoubleExpression => dExp.evaluate().toString
      case bExp: BooleanExpression => bExp.evaluate().toString
    }
  }
}