package utils

import models.{Observation, Item, Patient}
import java.util.Date


object PatientHelper {
  def inject(patient: Patient, observationList: List[String], timestamp: Date) = {
    val observations = patient.observations
    val merged = observations.zipAll(observationList, Observation(Nil), "")

    val newObservations = merged.map { case(obsList, entry) =>
        if (!entry.isEmpty) {
          Observation(Item(entry, timestamp) :: obsList.items)
        } else {
          Observation(obsList.items)
        }
    }
    newObservations
 }
}