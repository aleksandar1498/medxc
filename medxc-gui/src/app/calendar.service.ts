import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import { urlBuilder } from './util/ServiceUrlBuilder';
import { Appointment } from './model/appointment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {DoctorsService} from "./doctors.service";
import {Event} from './model/event';
@Injectable({
  providedIn: 'root'
})
export class CalendarService {

  constructor(private httpClient: HttpClient,private doctorService:DoctorsService){

  }
  /*
    According to the day , the end of the event will be set
    */
  calendarEvents: Event[] = [
    new Event('Appointment A',"2020-01-03 15:30","2019-12-15 15:45" ),
    new Event('Appointment Suray',"2020-01-02 15:30" ),
    new Event('Appointment B', "2020-01-01 13:10")
  ];
  getEvents(): Observable<any> {
    return of(this.calendarEvents);
  }

  addAppointment(st: any, en: any) {
    this.calendarEvents.push({ title: 'Appointment', start: st.format("YYYY-MM-DD HH:mm"), end: en.format("YYYY-MM-DD HH:mm") })
  }

  findAppointments(id:string,st:string,en:string):Observable<Appointment[]>{
   return  this.doctorService.getAppointments(id,st,en);
  }
}


