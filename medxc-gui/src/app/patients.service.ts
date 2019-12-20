import { Injectable } from '@angular/core';
import { Patient } from './model/patient';
import { Record } from './model/record';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Appointment } from './model/appointment';
import { urlBuilder } from './util/ServiceUrlBuilder';
import { PatientHistory } from './model/patienthistory';
import { Test } from './model/test';
import { Specialty } from './model/specialty';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class PatientsService {
  patients: Patient[];
  constructor(private httpClient: HttpClient) { }

  getRecords(id: string): Observable<Record[]> {
    return this.httpClient.get<Record[]>(urlBuilder(`/patients/records/${id}`));
  }

  getAppointmentsBySpecialty(id: string, specialty: string): Observable<Appointment[]> {
    return this.httpClient.get<Appointment[]>(urlBuilder(`/patients/appointments/${id}/${specialty}`));
  }

  getAppointments(id: string): Observable<Appointment[]> {
    return this.httpClient.get<Appointment[]>(urlBuilder(`/patients/appointments/${id}`));
  }

  getSpecialtiesByAppointments(id: string): Observable<Specialty[]> {
    return this.httpClient.get<Specialty[]>(urlBuilder(`/patients/specialties/${id}`));
  }

  getHistory(id: string): Observable<PatientHistory> {
    return this.httpClient.get<PatientHistory>(urlBuilder(`/patients/history/${id}`));

  }

  registerPatientAsync(newPatient: Patient): Observable<boolean> {
    return this.httpClient.post<boolean>(urlBuilder(`/patients/register`), newPatient, httpOptions);
  }

  getTests(id: string): Observable<Test[]> {
    return this.httpClient.get<Test[]>(urlBuilder(`/patients/tests/${id}`));
  }

  getPatients(): Observable<Patient[]> {
    return this.httpClient.get<Patient[]>(urlBuilder(`/patients`));
  }
}
