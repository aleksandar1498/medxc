import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PatientsComponent } from './/patients/patients.component';
import { DoctorsComponent } from './doctors/doctors.component';
import { PatientRecordsComponent } from './patient-records/patient-records.component';
import { AppointmentsComponent } from './appointments/appointments.component';
import { PatientHistoryComponent } from './patient-history/patient-history.component';
import { AdminsComponent } from './admins/admins.component';
import { PatientRegisterComponent } from './patient-register/patient-register.component';
import { PatientTestsComponent } from './patient-tests/patient-tests.component';



const routes: Routes = [
  { path: 'patients', component: PatientsComponent,data: {animation: 'PatientPage'}  },
  { path: 'patients/register', component: PatientRegisterComponent },
  { path: 'admins/doctors', component: AdminsComponent },
  { path: 'records/patients/:id', component: PatientRecordsComponent },
  { path: 'history/patients/:id', component: PatientHistoryComponent },
  { path: 'doctors', component: DoctorsComponent },
  { path: 'appointments/patients/:id/:specialty', component: AppointmentsComponent ,data: {animation: 'AppointmentsPage'} },
  { path: 'doctors/:id/:fromDate/:toDate', component: DoctorsComponent },
  { path: 'tests/patients/:id', component: PatientTestsComponent },
  {
    path: '',
    redirectTo: 'doctors',
    pathMatch: 'full'
  },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
