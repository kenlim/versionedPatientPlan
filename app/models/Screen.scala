package models

import play.api.Play.current
import java.util.Date
import com.novus.salat._
import com.mongodb.casbah.Imports._
import se.radley.plugin.salat._
import com.novus.salat.dao._

case class Patient(
  id: ObjectId = new ObjectId,
  name: String,
  observations: List[Observation]
)

case class Observation(
  items: Item
)

case class Item(
  text: String,
  author: String
)

object PatientDao extends SalatDAO[Patient, ObjectId](collection = mongoCollection("patientrecord")) {
  def all = find(MongoDBObject())
}
