import models.Patient
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
      val blankPatient = Patient(new ObjectId, "Martha")

      PatientHelper.inject(blankPatient, obsList)
      blankPatient.observations must have size(2)
    }
  }
}