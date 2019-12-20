import { Component, OnInit } from '@angular/core';
import { Doctor } from '../model/doctor';
// import { RouteConfigLoadEnd } from '@angular/router';
import { ActivatedRoute, Router } from '@angular/router';
import { DoctorsService } from '../doctors.service';
import { Appointment } from '../model/appointment';
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.css'],
  providers: [DatePipe],
})

export class DoctorsComponent implements OnInit {
  tableColumns: string[] = ['Date', 'Time', 'Doctor','Patient', 'Specialty', 'Status'];
  doctors: Doctor[]; // reference of real doctors from service
  appointments: Appointment[];
  doctor: Doctor;
  private loading: boolean;
  fromDate = new Date();
  toDate = new Date();
  model = new Date();
  id : string;

  constructor(private service: DoctorsService, private datePipe: DatePipe, private route: ActivatedRoute, private router: Router) {
    this.route.params.subscribe(
      params => {
        if (params['id'] && params['from'] && params['to']) {
          this.viewAppointments(params['id'], params['from'], params['to']);
        }
      });
    console.log('doctors component constructor...');
  }

  ngOnInit() {
    console.log('doctors component init...');
    this.loading = true;

    this.service.getDoctorsAsync().subscribe(ds => { this.doctors = ds; console.log('doctors loaded...'); this.loading = false; });
    console.log('doctors component init... done');
    this.doctors = this.service.getAllDoctors();
  }

  viewAppointments(id: string, from: Date, to: Date) {
    const fromDateString = this.datePipe.transform(this.fromDate, 'yyyy-MM-dd');
    const toDateString = this.datePipe.transform(this.toDate, 'yyyy-MM-dd');
    this.service.getAppointments(id, fromDateString, toDateString).subscribe(apps => {
      this.appointments = apps;
      console.log(this.appointments);
    });
  }

  onClickViewAppointments(id: string, from: Date, to: Date) {
    this.router.navigate(['doctor', id,this.fromDate,this.toDate]);
  }
}
