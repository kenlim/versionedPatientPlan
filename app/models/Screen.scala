package models

import play.api.Play.current
import java.util.Date
import com.novus.salat._
import com.mongodb.casbah.Imports._
import se.radley.plugin.salat._
import com.novus.salat.dao._
import utils.PatientHelper

case class Patient(
  id: ObjectId = new ObjectId,
  name: String,
  observations: List[Observation],
  assessments: List[Assessment]
)

case class Assessment(
  text: String,
  date: Date
)

case class Observation(
  items: List[Item]
)

case class ObservationFormOutput(
  assessment: String,
  obs: List[String]
)

case class Item(
  entry: String,
  date: Date)

object PatientDao extends SalatDAO[Patient, ObjectId](collection = mongoCollection("patientrecord")) {
  def all = find(MongoDBObject())
  def save(patient: Patient, providedObs: ObservationFormOutput) {
    val now = new Date()
    var obs = providedObs.obs
    println(obs)
    if (obs.last == "") {
      obs = obs.dropRight(1)
    }
    val newObservations = PatientHelper.inject(patient, obs, now)
    val newAssessment = Assessment(providedObs.assessment, now)
    save(Patient(patient.id, patient.name, newObservations, newAssessment :: patient.assessments))
  }
}
