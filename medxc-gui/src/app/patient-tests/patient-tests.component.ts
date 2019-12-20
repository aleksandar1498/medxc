import { Component, OnInit } from '@angular/core';
import { Test } from '../model/test';
import { HttpClient } from '@angular/common/http';
import { PatientsService } from '../patients.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-patient-tests',
  templateUrl: './patient-tests.component.html',
  styleUrls: ['./patient-tests.component.css']
})
export class PatientTestsComponent implements OnInit {
  tableColumns: string[] = ['Date','Type', 'Description' ];
  tests: Test[];
  id: string;
  
  constructor(
    private readonly service: PatientsService,
    private route: ActivatedRoute,
    public dialog: MatDialog,
    private router: Router) { }

  
  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.getTests(this.id);
  }
  getTests(id: string) {
    this.service.getTests(id).subscribe(res => {
      console.log(res);
      this.tests = res;
    });
  }
  
}
