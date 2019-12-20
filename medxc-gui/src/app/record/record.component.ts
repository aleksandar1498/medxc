import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
//import { Appointment } from '../model/appointment';

@Component({
  selector: 'app-record',
  templateUrl: './record.component.html',
  styleUrls: ['./record.component.css']
})
export class RecordComponent implements OnInit {
  constructor(public dialogRef: MatDialogRef<RecordComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {
    console.log('data', this.data);
  }

  ngOnInit() {
  }

}
