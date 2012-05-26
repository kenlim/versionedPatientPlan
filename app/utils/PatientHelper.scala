package utils

import models.{Observation, Item, Patient}


object PatientHelper {
  def inject(patient: Patient, observationList: List[String]) = {
    val observations = patient.observations
    val merged = observations.zipAll(observationList, Observation(Nil), "")

    val newObservations = merged.map { case(obsList, entry) =>
          Observation(Item(entry) :: obsList.items)
    }

    Patient(patient.id, patient.name, newObservations)
 }
}