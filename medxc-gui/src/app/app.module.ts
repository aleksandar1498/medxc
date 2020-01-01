import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { PatientsComponent } from './patients/patients.component';
import { DoctorsComponent } from './doctors/doctors.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatSliderModule } from '@angular/material/slider';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { PatientRecordsComponent } from './patient-records/patient-records.component';
import { AppointmentsComponent } from './appointments/appointments.component';
import { AdminsComponent } from './admins/admins.component';
import { PatientHistoryComponent } from './patient-history/patient-history.component';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { PatientRegisterComponent } from './patient-register/patient-register.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTableModule } from '@angular/material/table';
import { RecordComponent } from './record/record.component';
import { GlobalErrorHandler } from './global-error-handler';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { ServerErrorInterceptor } from './server-error.interceptor';
import {
  MatToolbarModule,
  MatIconModule,
  MatButtonModule,
  MatCardModule,
  MatListModule,
  MatGridListModule,
  MatDividerModule,
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule,
  MatRadioModule
} from '@angular/material';
import { PatientTestsComponent } from './patient-tests/patient-tests.component';
import {FullCalendarModule} from "@fullcalendar/angular";
import { DoctorsAppointmentsCalendarComponent } from './doctors-appointments-calendar/doctors-appointments-calendar.component';
import { EventDialogComponent } from './event-dialog/event-dialog.component';

const materialModules = [
  MatToolbarModule,
  MatIconModule,
  MatButtonModule,
  MatCardModule,
  MatListModule,
  MatGridListModule,
  MatDividerModule,
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule,
  MatRadioModule,
  MatDialogModule,
  MatTableModule,
  MatTabsModule,
  MatSnackBarModule,
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    PatientsComponent,
    DoctorsComponent,
    PatientRecordsComponent,
    AppointmentsComponent,
    AdminsComponent,
    PatientHistoryComponent,
    PatientRegisterComponent,
    RecordComponent,
    PatientTestsComponent,
    DoctorsAppointmentsCalendarComponent,
    EventDialogComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    MatSliderModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    MatTableModule,
    MatTabsModule,
    FullCalendarModule,
    ...materialModules,



  ],
  providers: [
    DatePipe,
    { provide: ErrorHandler, useClass: GlobalErrorHandler },
    { provide: HTTP_INTERCEPTORS, useClass: ServerErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent],
  entryComponents: [RecordComponent,EventDialogComponent]
})
export class AppModule { }
