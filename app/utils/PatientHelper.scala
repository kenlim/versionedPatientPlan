package utils

import models.{Observation, Item, Patient}


object PatientHelper {
  def inject(patient: Patient, observationList: List[String]) = {
    val observations = patient.observations
    val merged = observations.zipAll(observationList, Observation(Nil), "")

    val newObservations = merged.map { case(obsList, entry) =>
        if (!entry.isEmpty) {
          Observation(Item(entry) :: obsList.items)
        } else {
          Observation(obsList.items)
        }
    }

    Patient(patient.id, patient.name, newObservations)
 }
}