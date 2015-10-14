package code.comet

import net.liftweb.actor.LiftActor
import net.liftweb.common.SimpleActor
import net.liftweb.http.js.jquery.JqJsCmds.{AppendHtml, PrependHtml}
import net.liftweb.http.{ListenerManager, CometListener, CometActor}

/**
 * Created by Riccardo Sirigu on 14/10/15.
 */
class OrdersBoard extends CometActor with CometListener {

  override def render = "#orders" #> ""

  override protected def registerWith  = OrdersDispatcher

  override def lowPriority = {
    case o: Order => {
      partialUpdate(AppendHtml("order", <li class="list-group-item"><span>{o.dish} per {o.username}</span></li>))
    }
  }

}

object OrdersDispatcher extends LiftActor with ListenerManager {

  override protected def createUpdate = None

  override def lowPriority = {
    case o: Order => {
      sendListenersMessage(o)
    }
  }
}


case class Order(dish: String, username: String)
