package controllers

import play.api._
import data.Form
import data.Forms._
import play.api.mvc._
import play.api.Logger
import org.bson.types.ObjectId
import models.{Patient, PatientDao}


object PatientApp extends Controller with Secured {

  def index = withAuth { user => implicit request =>
    Ok(views.html.patient.index(PatientDao.all.toList, newPatientForm))
  }

  def view(id: ObjectId) = Action {
      PatientDao.findOneByID(id).map( patient =>
        Ok(views.html.patient.view(patient))
      ).getOrElse(NotFound)
    }

  def create = withAuth { user => implicit request =>
    newPatientForm.bindFromRequest.fold(
      errors => BadRequest(views.html.patient.index(PatientDao.all.toList, errors)),
      patient => {
        PatientDao.save(patient)
        Logger.info("Created patient: %s".format(patient.name))
        Redirect(routes.PatientApp.index)
      }
    )
  }

  val newPatientForm = Form(
    mapping(
      "name" -> text
    ){
      (name) => Patient(new ObjectId(), name, Nil)
    }{
      patient => Some(patient.name)
    }
  )
}