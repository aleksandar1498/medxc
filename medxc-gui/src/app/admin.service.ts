import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Doctor } from './model/doctor';
import { Observable } from 'rxjs';
import { urlBuilder } from './util/ServiceUrlBuilder';
import { Specialty } from './model/specialty';
import { delay } from 'rxjs/internal/operators/delay';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  doctors: Doctor[]; // cache
  constructor(private httpClient: HttpClient) { }

  getAllDoctors(): Doctor[] {
    return this.doctors;
  }

  getDoctorsAsync(): Observable<Doctor[]> {
    return this.httpClient.get<Doctor[]>(urlBuilder('/admins'));
  }

  createDoctor(id: string, name: string, email: string, phoneNumber: string, active: boolean, specialties: Specialty[]):
    Observable<boolean> {

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    const doctor = new Doctor(id, name, email, phoneNumber, false, specialties, null);
    console.log(JSON.stringify(doctor));
    return this.httpClient.post<boolean>(urlBuilder(`/admins/doctors/create`), doctor, httpOptions);
  }
  getSpecialtyNames(): Observable<string[]> {
    return this.httpClient.get<string[]>(urlBuilder(`/admins/getSpecialties`));
  }
}
