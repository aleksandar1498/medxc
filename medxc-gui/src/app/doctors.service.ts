import { Injectable } from '@angular/core';
import { Doctor } from './model/doctor';
import { Observable, of } from 'rxjs';
import { delay, find, map } from 'rxjs/operators';

import { HttpClient } from '@angular/common/http';
import { Specialty } from './model/specialty';
import { urlBuilder } from './util/ServiceUrlBuilder';
import { Appointment } from './model/appointment';


@Injectable({
  providedIn: 'root'
})
export class DoctorsService {

  doctors: Doctor[]; // cache

  toggleActive(id: string) {
    const doc = this.doctors.find(d => d.id === id);
    doc.active = !doc.active;
  }

  constructor(private httpClient: HttpClient) { }

  getAllDoctors(): Doctor[] {
    return this.doctors;
  }

  getDoctorsAsync(): Observable<Doctor[]> {
    return this.httpClient.get<Doctor[]>(urlBuilder('/doctors'));
  }

  getAppointments(id: string, from:string, to:string): Observable<Appointment[]> {
    return this.httpClient.get<Appointment[]>(urlBuilder(`/doctors/appointments/${id}/${from}/${to}`));
  }

  // deleteDoctor(id: number): Observable<Doctor[]> {
  //   return this.httpClient.delete<Doctor[]>(urlBuilder('/doctors/' + id));
  // }

  // createDoctor(id: string, name: string, email: string, phoneNumber: string, active: boolean): Observable<Doctor[]> {

  //   // const httpOptions = {
  //   //   headers: new HttpHeaders({
  //   //     'Content-Type': 'application/json'
  //   //   })
  //   // };
  //   var doctor = new Doctor(id, name, email, phoneNumber, true, [new Specialty('Cardiologist')]);

  //   //   //return this.httpClient.post<Doctor[]>(`http://localhost:8080/medic/rest/doctors/post`,doctor,httpOptions);
  //   return this.httpClient.post<Doctor[]>(urlBuilder(`/admins/create`), doctor);
  // }

  // addDoctor(id: number, name: string, rating: number, active: boolean): Observable<Doctor[]> {
  //   // MUST BE CHANGED !!!! PROBABLY WILL BE USING addDoctorOld(...)
  //   const httpOptions = {
  //     headers: new HttpHeaders({
  //       'Content-Type': 'application/x-www-form-urlencoded'
  //     })
  //   };


  //   let options = new URLSearchParams();
  //   options.set('id', id.toString());
  //   options.set('name', name);
  //   options.set('rating', rating.toString());
  //   options.set('active', active.toString());

  //   console.log(options);

  //   return this.httpClient.post<Doctor[]>(`http://localhost:8080/medic/rest/doctors/post`, options.toString(), httpOptions);
  //   // return this.httpClient.post<Doctor[]>(`http://localhost:8080/medic/rest/doctors/post`, options.toString(), httpOptions);
  // }

  // replaceDoctor(id: number, name: string, rating: number, active: boolean): Observable<Doctor[]> {
  //   // NEEDS TO BE CHANGED
  //   const httpOptions = {
  //     headers: new HttpHeaders({
  //       'Content-Type': 'application/x-www-form-urlencoded'
  //     })
  //   };
  //   let options = new URLSearchParams();
  //   options.set('id', id.toString());
  //   options.set('name', name);
  //   options.set('rating', rating.toString());
  //   options.set('active', active.toString());
  //   // var doctor = new Doctor(id, name, rating);

  //   return this.httpClient.put<Doctor[]>(urlBuilder(`doctors/put`), options.toString(), httpOptions);
  // }

  // changeActiveDoctor(id: number): Observable<Doctor[]> {
  //   // NEEDS TO BE CHANGED
  //   const httpOptions = {
  //     headers: new HttpHeaders({
  //       'Content-Type': 'application/x-www-form-urlencoded'
  //     })
  //   };
  //   let options = new URLSearchParams();
  //   options.set('id', id.toString());
  //   //var doctor = new Doctor(id, name, rating);

  //   return this.httpClient.patch<Doctor[]>(urlBuilder(`doctors/patch/`), options, httpOptions);
  // }


}
