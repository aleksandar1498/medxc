import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CalendarService {
  /*
    According to the day , the end of the event will be set
    */
  calendarEvents: any[] = [
    { title: 'Appointment A', start: "2019-12-15 05:30", end: "2019-12-15 06:45" },
    { title: 'Appointment Suray', start: "2019-12-20 06:30" },
    { title: 'Appointment B', start: "2019-12-17 13:10" }
  ];
  getEvents(): Observable<any> {
    return of(this.calendarEvents);
  }

  addAppointment(st: any, en: any) {
    this.calendarEvents.push({ title: 'Appointment', start: st.format("YYYY-MM-DD HH:mm"), end: en.format("YYYY-MM-DD HH:mm") })
  }
}
