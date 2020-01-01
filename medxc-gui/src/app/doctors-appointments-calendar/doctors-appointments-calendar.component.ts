import {Component, OnInit, ViewChild} from '@angular/core';
import {FullCalendarComponent} from "@fullcalendar/angular";
import {MatDialog} from "@angular/material/dialog";
import {MatDatepickerInputEvent} from "@angular/material/datepicker";
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGrigPlugin from '@fullcalendar/timegrid';
import * as moment from 'moment';
import interactionPlugin from '@fullcalendar/interaction'; // for dateClick
import { CalendarService } from '../calendar.service';
import {EventDialogComponent} from "../event-dialog/event-dialog.component";
import {DatePipe} from "@angular/common";
import {Event} from '../model/event';

@Component({
  selector: 'app-doctors-appointments-calendar',
  templateUrl: './doctors-appointments-calendar.component.html',
  styleUrls: ['./doctors-appointments-calendar.component.css']
})
export class DoctorsAppointmentsCalendarComponent{

  @ViewChild('calendar', { 'static': false })
  calendarComponent: FullCalendarComponent; // the #calendar in the template*/
  calendarPlugins = [dayGridPlugin, timeGrigPlugin, interactionPlugin];
  calendarVisible = true;
  calendarEvents: Event[] = [
  ];
  fromDate = new Date();
  toDate = new Date();
  id : string;
  constructor(
    private calendarService: CalendarService,
    public dialog: MatDialog, private datePipe: DatePipe) {
  }

  ngOnInit() {
     this.renderAppointments();
  }

  setDate(evt: MatDatepickerInputEvent<Date>) {
    let moments = moment(evt.value);
    let calendarApi = this.calendarComponent.getApi();
    calendarApi.gotoDate(moments.format("YYYY-MM-DD")); // call a method on the Calendar object*/
  }

  handleDateClick(arg): void {
    let now = moment(arg.date);

    let data = { id: "alex", start: arg.date, durations: {}, keys: [] };

    data.durations = {
      '15': now.add(15, "minutes").local().format(),
      '30': now.add(15, "minutes").local().format(),
      '45': now.add(15, "minutes").local().format()
    };
    data.keys = Object.keys(data.durations);
    const dialogRef = this.dialog.open(EventDialogComponent, {
      data,
      width: '550px',
    });

    dialogRef.afterClosed().subscribe(
      data => {
        if (data == undefined) {
          return;
        }
        let start = moment(data.start);
        let end = moment(data.end);
        this.calendarService.addAppointment(start, end);
        this.renderAppointments();
      }
    );
  }

  renderAppointments(): void {
    this.calendarService.getEvents().subscribe(data => {

      // JOIN the data, this kind of change is needed to trigger rerender of the calendar
      this.calendarEvents = Object.assign([], this.calendarEvents, data);
    });

  }
  viewAppointments(id):void{

    const fromDateString = this.datePipe.transform(this.fromDate, 'yyyy-MM-dd');
    const toDateString = this.datePipe.transform(this.toDate, 'yyyy-MM-dd');
    this.calendarService.findAppointments(id, fromDateString, toDateString).subscribe(apps => {
      let events:Event[] = apps.map(a => {
        return new Event(a.status,this.datePipe.transform(a.date,'yyyy-MM-dd HH:mm'));
      });
      this.calendarEvents = Object.assign([], this.calendarEvents, events);
      console.log(events);
    });
  }


}
