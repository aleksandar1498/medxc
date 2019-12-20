import { Component, OnInit } from '@angular/core';
import { PatientsService } from '../patients.service';
import { Router } from '@angular/router';
import { Doctor } from '../model/doctor';
import { Record } from '../model/record';
import { MatDialog } from '@angular/material/dialog';
import { Appointment } from '../model/appointment';
import { Test } from '../model/test';
import { Patient } from '../model/patient';
import { urlBuilder } from '../util/ServiceUrlBuilder';
@Component({
  selector: 'app-patients',
  templateUrl: './patients.component.html',
  styleUrls: ['./patients.component.css']
})
export class PatientsComponent implements OnInit {
  tableColumns: string[] = ['Id', 'Name', 'PhoneNumber', 'Email', 'Sex', 'SeeAppointments', 'SeeHistory','SeeTests','SeeRecords'];
  patients: Patient[];
  doctor: Doctor;
  records: Record[];
  appointments: Appointment[];
  tests: Test[];

  constructor(private service: PatientsService, private router: Router, public dialog: MatDialog) { }

  ngOnInit() {
    this.service.getPatients().subscribe(res => {
      console.log(res);
      this.patients = res;
    });

  }
  showAppointment(id: string) {
    console.log(id);
    const url = `/appointments/patients/${id}/None`;
    this.router.navigate([url]);
  }

  showRecords(id:string){
    console.log(id);
    let url = `/records/patients/${id}`;
    this.router.navigate([url]);
  }
  
  showHistory(id: string) {
    console.log(id);
    const url = `/history/patients/${id}`;
    this.router.navigate([url]);
  }
  
  showTests(id: string) {
    console.log(id);
    const url = `/tests/patients/${id}`;
    this.router.navigate([url]);
  }

}

