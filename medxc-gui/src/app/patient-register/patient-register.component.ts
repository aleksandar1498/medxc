import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, ValidatorFn, AbstractControl, FormBuilder, ValidationErrors, NgForm } from '@angular/forms';
import { PatientsService } from '../patients.service';
import { Patient } from '../model/patient';
import { passwordMatchValidator } from '../util/custom-validators';
import { MatRadioButton, MatRadioChange } from '@angular/material/radio';

@Component({
  selector: 'app-patient-register',
  templateUrl: './patient-register.component.html',
  styleUrls: ['./patient-register.component.css']
})
export class PatientRegisterComponent implements OnInit {
  isCreated = false;
  isPINUsed = false;
  loading = false;
  patient: Patient = new Patient('', '', '', '', '', '', []);
  profileForm = new FormGroup({
    id: new FormControl('', [Validators.required, Validators.maxLength(10)]),
    name: new FormControl('', [Validators.required, Validators.maxLength(70)]),
    password: new FormControl('', [Validators.required, Validators.minLength(7)]),
    confirmPassword: new FormControl('', { validators: Validators.required, updateOn: 'blur' }),
    email: new FormControl('', [Validators.required, Validators.email, Validators.maxLength(50)]),
    phoneNumber: new FormControl('', [Validators.required,
    Validators.pattern('^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[- \./0-9]*$'), Validators.maxLength(20)]),
    sex: new FormControl('', Validators.required),
    address: new FormControl('', [Validators.maxLength(100)])
  }, { validators: passwordMatchValidator });

  constructor(private service: PatientsService) { }

  ngOnInit() {
  }

  get id() {
    return this.profileForm.get('id');
  }
  get name() {
    return this.profileForm.get('name');
  }
  get email() {
    return this.profileForm.get('email');
  }
  get phoneNumber() {
    return this.profileForm.get('phoneNumber');
  }

  get address() {
    return this.profileForm.get('address');
  }

  get sex() {
    return this.profileForm.get('sex');
  }

  resetForm(form: NgForm) {
    form.resetForm({});
    this.isCreated = false;
    this.isPINUsed = false;
  }

  registerPatient(form: NgForm) {
    // console.log('Register Patient Triggered');
    this.patient = new Patient(this.id.value, this.name.value, this.email.value, this.sex.value,
      this.phoneNumber.value, this.address.value, []);
    this.isCreated = false;
    this.loading = true;
    this.isPINUsed = false;
    this.service.registerPatientAsync(this.patient).subscribe(
      p => {
        console.log(p);
        if (p) {
          this.isCreated = true;
          form.resetForm({});
        } else {
          this.isPINUsed = true;
        }
        this.loading = false;
      },
      error => {
        console.log(JSON.stringify(error));
        this.loading = false;
      });
  }
}
