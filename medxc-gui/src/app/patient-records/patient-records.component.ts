import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Record } from '../model/record';
import { PatientsService } from '../patients.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-patient-records',
  templateUrl: './patient-records.component.html',
  styleUrls: ['./patient-records.component.css']
})
export class PatientRecordsComponent implements OnInit {
  records: Record[];
  id: string;
  constructor(private httpClient: HttpClient, private patientService: PatientsService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.getRecords(this.id);
  }

  getRecords(id: string) {
    this.patientService.getRecords(id).subscribe(res => {
      console.log(res);
      this.records = res;
    });
  }
}
