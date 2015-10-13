package code
package snippet

import code.model.Food
import net.liftweb.common.Full
import scala.xml._
import net.liftweb.util.BindHelpers._



class ListFood {

  var html = Food.getPizza match {
    case Full(f:Food) => f.toXHtml
    case _ => <p> NO PIZZA, SORRY</p>
  }


  def render = "#listpizza *" #> Food.findAll.map{_.toXHtml}




}