import models.{Observation, Patient}
import org.bson.types.ObjectId
import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import utils.PatientHelper

class PatientFillerSpec extends Specification {

  "The 'Hello world' string" should {
    "contain 11 characters" in {
      "Hello world" must have size (11)
    }
    "start with 'Hello'" in {
      "Hello world" must startWith("Hello")
    }
    "end with 'world'" in {
      "Hello world" must endWith("world")
    }
  }

  "The list of observations" should {
    "inject themselves into the Patient list" in {
      val obsList = "Hello" :: "World" :: Nil
      val blankPatient = Patient(new ObjectId, "Martha", Nil)

      val newPatient = PatientHelper.inject(blankPatient, obsList)
      newPatient.observations.foreach {println(_)}
      newPatient.observations must have size(2)

      val obsList2 = "workit" :: Nil
      val newPatient2 = PatientHelper.inject(newPatient, obsList2)
      newPatient2.observations.foreach {println(_)}
      newPatient2.observations must have size(2)

    }
  }
}