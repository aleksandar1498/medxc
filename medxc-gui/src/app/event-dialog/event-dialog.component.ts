import { Component, Inject } from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {OnInit} from '@angular/core';
import{ FormGroup ,FormBuilder} from '@angular/forms';

@Component({
  selector: 'app-event-dialog',
  templateUrl: './event-dialog.component.html',
  styleUrls: ['./event-dialog.component.css']
})
export class EventDialogComponent implements OnInit{
  form: FormGroup;
  constructor(public dialogRef: MatDialogRef<EventDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private fb: FormBuilder) {
    console.log(this.data);
    console.log(this.data.keys);
  }
  createAppointment(){
    this.dialogRef.close(this.form.value);
  }
  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(){
    this.form = this.fb.group({
      start: [''],
      end: ['']
    });
  }

}
