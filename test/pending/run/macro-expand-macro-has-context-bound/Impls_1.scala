import scala.reflect.macros.{Context => Ctx}

object Impls {
  def foo[U](c: Ctx)(x: c.Expr[U])(evidence: c.Expr[Numeric[U]]) = {
    import c.universe._
    val plusOne = Apply(Select(evidence.tree, TermName("plus")), List(x.tree, Literal(Constant(1))))
    val body = Apply(Select(Ident(definitions.PredefModule), TermName("println")), List(plusOne))
    Expr[Unit](body)
  }
}