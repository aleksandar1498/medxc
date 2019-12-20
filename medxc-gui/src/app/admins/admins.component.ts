import { Component, OnInit } from '@angular/core';
import { Doctor } from '../model/doctor';
import { DoctorsService } from '../doctors.service';
import { AdminService } from '../admin.service';
import { Specialty } from '../model/specialty';

@Component({
  selector: 'app-admins',
  templateUrl: './admins.component.html',
  styleUrls: ['./admins.component.css']
})
export class AdminsComponent implements OnInit {

  doctors: Doctor[]; // reference of real doctors from service

  doctor: Doctor;
  private created: boolean;
  private loading: boolean;
  private specialtiesLoaded: string[];
  private choosedSpecialties: Array<string> = [];
  private specialties: Array<Specialty> = [];

  constructor(private service: AdminService) {
    console.log('admin component constructor...');
  }

  ngOnInit() {
    this.loading = true;
    this.getSpecialtyNames();
    console.log('admin component init...');
    this.created = false;
  }

  createDoctor() {
    console.log('creating doctor..');

    const id = (document.getElementById(
      'idInput') as HTMLTextAreaElement).value;

    const name = (document.getElementById(
      'nameInput') as HTMLTextAreaElement).value;

    const email = (document.getElementById(
      'emailInput') as HTMLTextAreaElement).value;

    const phoneNumber = (document.getElementById(
      'phoneNumberInput') as HTMLTextAreaElement).value;
    this.specialties = [];
    for (const specialtyName of this.choosedSpecialties) {
      const current = new Specialty(specialtyName);
      this.specialties.push(current);
    }
    console.log(this.specialties);
    this.service.createDoctor(id, name, email, phoneNumber, false, this.specialties)
      .subscribe(res => {
        this.created = res;
        console.log(res); console.log('doctors loaded FROM POST...');
      });
  }

  chooseSpecialty(specialty: string) {
    const index = this.choosedSpecialties.indexOf(specialty);
    if (index === -1) {
      this.choosedSpecialties.push(specialty);
      console.log(this.choosedSpecialties);
    } else {
      this.choosedSpecialties.splice(this.choosedSpecialties.indexOf(specialty), 1);
      console.log(this.choosedSpecialties);
    }
  }

  getSpecialtyNames() {
    this.service.getSpecialtyNames().subscribe(res => { this.specialtiesLoaded = res; this.loading = false; });
  }
}
