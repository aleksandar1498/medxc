import {AfterViewChecked, AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
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
import * as $ from "jquery";
import {Calendar} from "@fullcalendar/core";
@Component({
  selector: 'app-doctors-appointments-calendar',
  templateUrl: './doctors-appointments-calendar.component.html',
  styleUrls: ['./doctors-appointments-calendar.component.css']
})
export class DoctorsAppointmentsCalendarComponent implements AfterViewInit {


 @ViewChild('calendar', { 'static': false })
 calendarComponent:ElementRef;
  calendar:Calendar;
  calendarPlugins = [dayGridPlugin, timeGrigPlugin, interactionPlugin];
  calendarVisible = true;
  calendarEvents: Event[] = [
 ];
  ngAfterViewInit() {
    console.log("Callsed after view init",this.calendarComponent.nativeElement);
    this.renderAppointments();
    this.initCalendar();

    console.log(this.calendarEvents);
  }
  fromDate = new Date();
  toDate = new Date();
  id : string;


  ngOnInit() {


  }
 constructor(
   private calendarService: CalendarService,
   public dialog: MatDialog, private datePipe: DatePipe) {

 }
 setDate(evt: MatDatepickerInputEvent<Date>) {
   let moments = moment(evt.value);
   let calendarApi = this.calendarComponent.nativeElement.getApi();
  calendarApi.gotoDate(moments.format("YYYY-MM-DD")); // call a method on the Calendar object*/
  }

  handleDateClick(arg) {


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



  private initCalendar() {
      this.calendar = new Calendar(this.calendarComponent.nativeElement,{
        header: {
          left   : 'prev,next today',
          center : 'title',
          right  : 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        slotDuration : "00:15:00",
        allDaySlot:false,
        eventTimeFormat:{ hour: '2-digit',minute: '2-digit',hour12: false},
        minTime :"07:30:00",
        maxTime : "19:30:00",
        defaultView : "timeGridWeek",
        plugins : this.calendarPlugins,
        navLinks   : true,
        editable   : true,
        eventLimit : true,
        events : this.calendarEvents,
        contentHeight :600,
      });

    this.calendar.render();



  }
}
