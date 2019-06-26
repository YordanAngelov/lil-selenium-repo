package tech.angelov.util.converters

import cucumber.api.Transformer
import cucumber.deps.com.thoughtworks.xstream.annotations.XStreamConverter

//class EitherTransformer[A, B] extends Transformer[Either[A, B]] {
//
//  override def transform(value: String): CukeEither[A, B] =
//    CukeEither(Either(value))
//
//}
//
//@XStreamConverter(classOf[EitherTransformer[A, B]])
//case class CukeEither[A, B](either: Either[A, B])

// TODO: Stolen from here: https://stackoverflow.com/questions/24448296/conversion-from-string-to-bigdecimal-do-not-with-cucumber-on-scala
//
//class MyBigDecimalTransformer extends Transformer[MyBigDecimal] {
//  override def transform(value: String): MyBigDecimal = {
//    MyBigDecimal(BigDecimal(value))
//  }}
//
//@XStreamConverter(classOf[MyBigDecimalTransformer])
//case class MyBigDecimal(value: BigDecimal) {
//}