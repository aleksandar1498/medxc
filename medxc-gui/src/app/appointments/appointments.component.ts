import { Component, OnInit } from '@angular/core';
import { Appointment } from '../model/appointment';
import { Specialty } from '../model/specialty';
import { PatientsService } from '../patients.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { RecordComponent } from '../record/record.component';
@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css']
})
export class AppointmentsComponent implements OnInit {

  tableColumns: string[] = ['Date', 'Time', 'Doctor', 'Specialty', 'Status', 'RecordButton'];

  appointments: Appointment[];
  specialties: Specialty[];
  id: string;
  specialty: string;

  constructor(
    private readonly service: PatientsService,
    private route: ActivatedRoute,
    public dialog: MatDialog,
    private router: Router) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.specialty = this.route.snapshot.paramMap.get('specialty');

    if (this.specialty === 'None') {
      this.service.getAppointments(this.id).subscribe(apps => {
        this.appointments = apps;
      });
    } else {
      this.service.getAppointmentsBySpecialty(this.id, this.specialty).subscribe(apps => {
        this.appointments = apps;
      });
    }

    this.service.getSpecialtiesByAppointments(this.id).subscribe(specs => {
      this.specialties = specs;
      console.log(this.specialties);
    });

  }

  filterBySpecialties(specialty: string) {
    specialty = specialty.trim();

    if (specialty === 'None') {
      this.router.navigate(['appointments', 'patients', this.id, specialty]);
      this.service.getAppointments(this.id).subscribe(apps => {
        this.appointments = apps;
      });
    } else {
      this.router.navigate(['appointments', 'patients', this.id, specialty]);
      this.service.getAppointmentsBySpecialty(this.id, specialty).subscribe(apps => {
        this.appointments = apps;
      });
    }
  }

  openRecord(appointment: Appointment) {
    const dialogRef = this.dialog.open(RecordComponent, {
      data: {
        doc_name: appointment.doctor.name,
        app_time: appointment.date,
        rec_anamnesis: appointment.record.anamnesis,
        rec_observation: appointment.record.observation,
        rec_therapy: appointment.record.therapy
      },
      width: '450px'
    });
  }

}
