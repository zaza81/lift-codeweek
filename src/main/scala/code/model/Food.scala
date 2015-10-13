package code.model

import code.lib.RogueMetaRecord
import net.liftweb.common.{Full, Box}
import net.liftweb.mongodb.record.{MongoMetaRecord, MongoRecord}
import net.liftweb.mongodb.record.field.ObjectIdPk
import net.liftweb.record.field.StringField

import scala.xml.NodeSeq


/**
 * Created by andreazanda on 13/10/15.
 */
class Food extends MongoRecord[Food] with ObjectIdPk[Food]{
  def meta = Food

  object name extends StringField (this, 300)
  object description extends StringField (this, 300)
  object image extends StringField (this, 300)


 override  def toXHtml: NodeSeq = {
   <div class="food">
   <h3>{this.name} </h3>
    <p> {this.description}</p>
     <img src={this.image.get}> </img>
   </div>


 }

}


object Food extends Food with  RogueMetaRecord[Food] {

  def getPizza: Box[Food] = Food.where(_.name eqs "pizza").get()

  def populateDB () ={

    Food.createRecord.name("pizza").description("the best food in the world").image("http://blog.edoapp.it/wp-content/uploads/2015/06/pizza-cut2.jpg").save(true)
    Food.createRecord.name("hamburger").description("Food to get fat").image("http://5tim.com/wp-content/uploads/2011/05/hamburger.jpg").save(true)


  }


}