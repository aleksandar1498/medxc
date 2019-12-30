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

@Component({
  selector: 'app-doctors-appointments-calendar',
  templateUrl: './doctors-appointments-calendar.component.html',
  styleUrls: ['./doctors-appointments-calendar.component.css']
})
export class DoctorsAppointmentsCalendarComponent implements OnInit {

  @ViewChild('calendar', { 'static': false })
  calendarComponent: FullCalendarComponent; // the #calendar in the template
  calendarPlugins = [dayGridPlugin, timeGrigPlugin, interactionPlugin];
  calendarVisible = true;
  calendarEvents: any[] = [
  ];
  data: any;
  constructor(
    private calendarService: CalendarService,
    public dialog: MatDialog) {

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
  addAppointment(app: any) {
    this.calendarEvents = this.calendarEvents.concat(app);
  }
  renderAppointments(): void {
    this.calendarService.getEvents().subscribe(data => {
      // JOIN the data, this kind of change is needed to trigger rerender of the calendar
      this.calendarEvents = Object.assign([], this.calendarEvents, data);

    });

  }

  resizeCalendar():void{
    console.log("resized");
  }

}
