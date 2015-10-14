package code.api

import code.comet.{OrdersDispatcher, Order}
import net.liftweb.http.LiftRules
import net.liftweb.http.rest.RestHelper
import net.liftweb.json.JsonAST.{JBool, JString}

/**
 * Created by Riccardo Sirigu on 14/10/15.
 */
object Api extends RestHelper {

  def init(): Unit = {
    LiftRules.statelessDispatch.append(Api)
  }


  serve{
    case "api" :: "test" :: Nil JsonGet _ => JString("Working!")

    case "api" :: "order" :: Nil  JsonPost json -> _ => {
      val order: Order = json.extract[Order]
      OrdersDispatcher ! order
      JBool(true)
    }
  }
}
