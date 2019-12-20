import { Component, OnInit } from '@angular/core';
import { PatientsService } from '../patients.service';
import { ActivatedRoute } from '@angular/router';
import { PatientHistory } from '../model/patienthistory';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-patient-history',
  templateUrl: './patient-history.component.html',
  styleUrls: ['./patient-history.component.css']
})
export class PatientHistoryComponent implements OnInit {
  history: PatientHistory;
  id: string;
  loaded: boolean;
  constructor(private httpClient: HttpClient, private patientService: PatientsService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    console.log(this.id);

    this.getHistory(this.id);
  }

  isLoaded() {
    return this.loaded;
  }

  getHistory(id: string) {
    this.history = new PatientHistory(null, null);
    this.patientService.getHistory(id).subscribe(res => {
      console.log(res);
      this.history = res;
    });
  }

  hasRecords(): boolean {
    if (this.history.records == null) {
      return false;
    }
    if (typeof this.history.records !== 'undefined' && this.history.records.length > 0) {
      return true;
    } else {
      return false;
    }
  }

  hasTests(): boolean {
    if (this.history.tests == null) {
      return false;
    }
    if (typeof this.history.tests !== 'undefined' && this.history.tests.length > 0) {
      return true;
    } else {
      return false;
    }
  }
}
